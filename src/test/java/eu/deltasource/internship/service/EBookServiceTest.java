package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.AuthorRepository;
import eu.deltasource.internship.repository.EBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EBookServiceTest {
    EBookService service = EBookService.getInstance();
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
        EBook harryPotter0 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertEquals(harryPotter0, harryPotter);
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertNull(harryPotter);
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        boolean successfulDelete = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

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
        tags.add(Tag.BOOK);

        // When
        boolean successfulRemove = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertFalse(successfulRemove);
    }

    @Test
    void testReturnAuthorsSetSuccessfully() {
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
        EBook harryPotter0 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        EBook harryPotter1 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-97", tags, "sth-2", null);
        eBookRepoInstance.add(harryPotter0);
        eBookRepoInstance.add(harryPotter1);

        // When
        Set<EBook> eBooks = eBookRepoInstance.getList();

        //Then
        assertEquals(2, eBookRepoInstance.getList().size());
        assertTrue(eBookRepoInstance.getList().contains(harryPotter0));
        assertTrue(eBookRepoInstance.getList().contains(harryPotter1));
    }
}