package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorRepositoryTest {

    AuthorRepository authorRepoInstance = AuthorRepository.getInstance();

    @AfterEach
    void clearAuthorRepo() {
        authorRepoInstance.clearRepository();
    }

    @Test
    void testAddAuthorSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // When
        Author successfulAdd = authorRepoInstance.add(IvanVazov);

        // Then
        assertEquals(1, authorRepoInstance.getList().size());
        assertTrue(authorRepoInstance.getList().contains(successfulAdd));
    }

    @Test
    void testAddDuplicateAuthorReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        Author successfulAdd = authorRepoInstance.add(IvanVazov);

        // When
        authorRepoInstance.add(IvanVazov);

        // Then
        assertEquals(1, authorRepoInstance.getList().size());
        assertTrue(authorRepoInstance.getList().contains(successfulAdd));
    }

    @Test
    void testRemoveAuthorSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        authorRepoInstance.add(IvanVazov);

        // When
        authorRepoInstance.remove(IvanVazov);

        // Then
        assertEquals(0, authorRepoInstance.getList().size());
    }

    @Test
    void testGetAuthorListSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        authorRepoInstance.add(IvanVazov);

        // When
        Set<Author> authors = authorRepoInstance.getList();

        // Then
        assertTrue(authors.contains(IvanVazov));
    }
}