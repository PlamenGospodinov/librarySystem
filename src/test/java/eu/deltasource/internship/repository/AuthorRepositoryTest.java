package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorRepositoryTest {

    AuthorRepository authorRepoInstance = AuthorRepository.getInstance();

    @Test
    void testAddAuthorSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));

        // when
        boolean successfulAdd = authorRepoInstance.addAuthor(IvanVazov);

        // then
        assertEquals(1, authorRepoInstance.getAuthorList().size());
        assertTrue(successfulAdd);
        authorRepoInstance.removeAuthor(IvanVazov);
    }

    @Test
    void testAddDuplicateAuthorReturnsFalse() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        boolean successfulAdd = authorRepoInstance.addAuthor(IvanVazov);

        // when
        boolean failedAdd = authorRepoInstance.addAuthor(IvanVazov);

        // then
        assertEquals(1, authorRepoInstance.getAuthorList().size());
        assertTrue(successfulAdd);
        assertFalse(failedAdd);
        authorRepoInstance.removeAuthor(IvanVazov);
    }

    @Test
    void testRemoveAuthorSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        authorRepoInstance.addAuthor(IvanVazov);

        // when
        authorRepoInstance.removeAuthor(IvanVazov);

        // then
        assertEquals(0, authorRepoInstance.getAuthorList().size());
    }

    @Test
    void testGetAuthorListSuccessfully() {
        // given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850,7,9), LocalDate.of(1921,9,22));
        authorRepoInstance.addAuthor(IvanVazov);

        // when
        List<Author> authors = authorRepoInstance.getAuthorList();

        // then
        assertEquals(authors.get(0), IvanVazov);
        authorRepoInstance.removeAuthor(IvanVazov);
    }
}