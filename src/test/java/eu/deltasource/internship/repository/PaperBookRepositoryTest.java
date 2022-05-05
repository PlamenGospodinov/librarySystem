package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookRepositoryTest {
    PaperBookRepository paperBookRepoInstance = PaperBookRepository.getInstance();

    @Test
    void testAddAPaperBookSuccessfully() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);

        // when
        boolean successfulAdd = paperBookRepoInstance.addBook(HarryPotter);

        // then
        assertEquals(1, paperBookRepoInstance.getPaperBooks().size());
        assertTrue(successfulAdd);
        paperBookRepoInstance.removeBook(HarryPotter);
    }

    @Test
    void testAddAPaperBookDuplicateReturnsFalse() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        boolean successfulAdd = paperBookRepoInstance.addBook(HarryPotter);

        // when
        boolean failedAdd = paperBookRepoInstance.addBook(HarryPotter);

        // then
        assertEquals(1, paperBookRepoInstance.getPaperBooks().size());
        assertTrue(successfulAdd);
        assertFalse(failedAdd);
        paperBookRepoInstance.removeBook(HarryPotter);
    }

    @Test
    void testRemovePaperBookSuccessfully() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.addBook(HarryPotter);

        // when
        paperBookRepoInstance.removeBook(HarryPotter);

        // then
        assertEquals(0, paperBookRepoInstance.getPaperBooks().size());
    }

    @Test
    void testGetPaperBookListSuccessfully() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.addBook(HarryPotter);

        // when
        List<PaperBook> paperBooks = paperBookRepoInstance.getPaperBooks();

        // then
        assertEquals(paperBooks.get(0), HarryPotter);
        paperBookRepoInstance.removeBook(HarryPotter);
    }
}