package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.AuthorRepository;
import eu.deltasource.internship.repository.EBookRepository;
import eu.deltasource.internship.service.EBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EBookControllerTest {
    EBookService service = EBookService.getInstance();
    EBookController controller = new EBookController();
    EBookRepository eBookRepoInstance = EBookRepository.getInstance();

    @AfterEach
    void clear() {
        eBookRepoInstance.clearRepository();
    }

    @Test
    void testCreateEBookSuccessfully() {
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
        EBook ironHeart = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook eBook = controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertEquals(ironHeart, eBook);
        assertEquals(1, eBookRepoInstance.getList().size());

    }

    @Test
    void testAddDuplicateEBookReturnsNull() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook eBook = controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertNull(eBook);
        assertEquals(1, eBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteEBookSuccessfully() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        boolean successfulDelete = controller.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertTrue(successfulDelete);
        assertEquals(0, eBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAuthorThatDoesntExistReturnsFalse() {
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
        boolean successfulDelete = controller.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertFalse(successfulDelete);
    }

    @Test
    void testReturnEBooksSetSuccessfully() {
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
        EBook ironHeart = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        EBook ironHeart2 = new EBook("RandomName2", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.add(ironHeart);
        eBookRepoInstance.add(ironHeart2);

        // When
        Set<EBook> eBooksSet = eBookRepoInstance.getList();

        // Then
        assertEquals(2, eBookRepoInstance.getList().size());
        assertTrue(eBookRepoInstance.getList().contains(ironHeart));
        assertTrue(eBookRepoInstance.getList().contains(ironHeart2));
    }
}
