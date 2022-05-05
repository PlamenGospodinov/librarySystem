package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EBookRepositoryTest {

    EBookRepository eBookRepoInstance = EBookRepository.getInstance();

    @Test
    void testAddAnEBookSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // when
        boolean successfulAdd = eBookRepoInstance.addBook(HarryPotter);

        // then
        assertEquals(1, eBookRepoInstance.getEBooks().size());
        assertTrue(successfulAdd);
        eBookRepoInstance.removeBook(HarryPotter);
    }

    @Test
    void testAddAnEBookDuplicateReturnsFalse() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        boolean successfulAdd = eBookRepoInstance.addBook(HarryPotter);

        // when
        boolean failedAdd = eBookRepoInstance.addBook(HarryPotter);

        // then
        assertEquals(1, eBookRepoInstance.getEBooks().size());
        assertTrue(successfulAdd);
        assertFalse(failedAdd);
        eBookRepoInstance.removeBook(HarryPotter);
    }

    @Test
    void testRemoveAuthorSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.addBook(HarryPotter);

        // when
        eBookRepoInstance.removeBook(HarryPotter);

        // then
        assertEquals(0, eBookRepoInstance.getEBooks().size());
    }

    @Test
    void testGetAuthorListSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.addBook(HarryPotter);

        // when
        List<EBook> eBooks = eBookRepoInstance.getEBooks();

        // then
        assertEquals(eBooks.get(0), HarryPotter);
        eBookRepoInstance.removeBook(HarryPotter);
    }

}