package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EBookRepositoryTest {

    EBookRepository eBookRepoInstance = EBookRepository.getInstance();

    @AfterEach
    void clearAuthorRepo() {
        eBookRepoInstance.clearRepository();
    }

    @Test
    void testAddAnEBookSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        boolean successfulAdd = eBookRepoInstance.add(HarryPotter);

        // Then
        assertEquals(1, eBookRepoInstance.getList().size());
        assertTrue(successfulAdd);
    }

    @Test
    void testAddAnEBookDuplicateReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        boolean successfulAdd = eBookRepoInstance.add(HarryPotter);

        // When
        boolean failedAdd = eBookRepoInstance.add(HarryPotter);

        // Then
        assertEquals(1, eBookRepoInstance.getList().size());
        assertTrue(successfulAdd);
        assertFalse(failedAdd);
    }

    @Test
    void testRemoveAnEBookSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.add(HarryPotter);

        // When
        eBookRepoInstance.remove(HarryPotter);

        // Then
        assertEquals(0, eBookRepoInstance.getList().size());
    }

    @Test
    void testGetEBookListSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        EBook HarryPotter = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.add(HarryPotter);

        // When
        Set<EBook> eBooks = eBookRepoInstance.getList();

        // Then
        assertTrue(eBooks.contains(HarryPotter));
    }
}