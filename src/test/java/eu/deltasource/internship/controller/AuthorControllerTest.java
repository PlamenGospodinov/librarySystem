package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.AuthorRepository;
import eu.deltasource.internship.service.AuthorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControllerTest {

    AuthorService service = AuthorService.getInstance();
    AuthorController controller = new AuthorController();
    AuthorRepository authorRepoInstance = AuthorRepository.getInstance();

    @AfterEach
    void clear() {
        authorRepoInstance.clearRepository();
    }

    @Test
    void testCreateAuthorSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // When
        Author author = controller.create("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // Then
        assertEquals(IvanVazov, author);
        assertEquals(1, authorRepoInstance.getList().size());

    }

    @Test
    void testAddDuplicateAuthorReturnsNull() {
        // Given
        controller.create("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // When
        Author author = controller.create("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // Then
        assertNull(author);
        assertEquals(1, authorRepoInstance.getList().size());
    }

    @Test
    void testDeleteAuthorSuccessfully() {
        // Given
        controller.create("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // When
        boolean successfulRemove = controller.delete("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // Then
        assertTrue(successfulRemove);
        assertEquals(0, authorRepoInstance.getList().size());
    }

    @Test
    void testDeleteAuthorThatDoesntExistReturnsFalse() {
        // Given

        // When
        boolean successfulRemove = controller.delete("Ivan", "Minchov", "Vazov", "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // Then
        assertFalse(successfulRemove);
    }

    @Test
    void testReturnAuthorsSetSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        Author IvanVazov2 = new Author(name, "Bulgaria", LocalDate.of(1860,7,9), LocalDate.of(1921,9,25));
        authorRepoInstance.add(IvanVazov);
        authorRepoInstance.add(IvanVazov2);

        // When
        Set<Author> authors = controller.getList();

        //Then
        assertEquals(2, authorRepoInstance.getList().size());
        assertTrue(authorRepoInstance.getList().contains(IvanVazov));
        assertTrue(authorRepoInstance.getList().contains(IvanVazov2));
    }
}