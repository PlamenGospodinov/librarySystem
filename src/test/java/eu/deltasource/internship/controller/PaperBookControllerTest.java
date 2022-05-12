package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.PaperBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookControllerTest {
    PaperBookController controller = new PaperBookController();
    PaperBookRepository paperBookRepoInstance = PaperBookRepository.getInstance();

    @AfterEach
    void clear() {
        paperBookRepoInstance.clearRepository();
    }

    @Test
    void testCreateAPaperBookSuccessfully() {
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
        PaperBook ironHeart = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        PaperBook book = controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertEquals(ironHeart, book);
        assertEquals(1, paperBookRepoInstance.getList().size());

    }

    @Test
    void testAddADuplicatePaperBookReturnsNull() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        PaperBook book = controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertNull(book);
        assertEquals(1, paperBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAPaperBookSuccessfully() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        boolean successfulDelete = controller.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertTrue(successfulDelete);
        assertEquals(0, paperBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAPaperBookThatDoesntExistReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);

        // When
        boolean successfulDelete = controller.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertFalse(successfulDelete);
    }

    @Test
    void testDeleteAPaperBookByIsbnSuccessfully() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        boolean successfulDelete = controller.deleteByIsbn("98-54-895-98");

        // Then
        assertTrue(successfulDelete);
        assertEquals(0, paperBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAPaperBookByInvalidIsbnReturnsFalse() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        boolean successfulDelete = controller.deleteByIsbn("98-74-895-98");

        // Then
        assertFalse(successfulDelete);
        assertEquals(1, paperBookRepoInstance.getList().size());
    }

    @Test
    void testReturnAPaperBookSetSuccessfully() {
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
        PaperBook ironHeart = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);
        PaperBook ironHeart2 = new PaperBook("RandomName2", authors, genres, "Sth small", "98-54-895-98", tags, 15);
        paperBookRepoInstance.add(ironHeart);
        paperBookRepoInstance.add(ironHeart2);

        // When
        Set<PaperBook> paperBooksSet = controller.getList();

        // Then
        assertEquals(2, paperBooksSet.size());
        assertTrue(paperBookRepoInstance.getList().contains(ironHeart));
        assertTrue(paperBookRepoInstance.getList().contains(ironHeart2));
    }
}