package eu.deltasource.internship.service;

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

class PaperBookServiceTest {
    PaperBookService service = PaperBookService.getInstance();
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
        PaperBook harryPotter0 = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        PaperBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertEquals(harryPotter0, harryPotter);
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        PaperBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertNull(harryPotter);
        assertEquals(1, paperBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAnEBookSuccessfully() {
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // When
        boolean successfulDelete = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

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
        tags.add(Tag.BOOK);

        // When
        boolean successfulRemove = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);

        // Then
        assertFalse(successfulRemove);
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
        PaperBook harryPotter0 = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);
        PaperBook harryPotter1 = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-97", tags, 15);
        paperBookRepoInstance.add(harryPotter0);
        paperBookRepoInstance.add(harryPotter1);

        // When
        Set<PaperBook> paperBooks = service.getList();

        //Then
        assertEquals(2, paperBooks.size());
        assertTrue(paperBooks.contains(harryPotter0));
        assertTrue(paperBooks.contains(harryPotter1));
    }

    @Test
    void testDeleteByIsbnIsSuccessful() {
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
        PaperBook harryPotter0 = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 15);
        paperBookRepoInstance.add(harryPotter0);

        // When
        boolean deleteIsSuccessful = service.deleteByIsbn("98-54-895-98");

        //Then
        assertEquals(0, paperBookRepoInstance.getList().size());
        assertTrue(deleteIsSuccessful);
    }

    @Test
    void testDeleteByIsbnReturnsFalseIfIsbnIsNotCorrect() {
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
        PaperBook harryPotter0 = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags,15);
        paperBookRepoInstance.add(harryPotter0);

        // When
        boolean deleteIsSuccessful = service.deleteByIsbn("98-55-895-98");

        //Then
        assertEquals(1, paperBookRepoInstance.getList().size());
        assertFalse(deleteIsSuccessful);
    }
}