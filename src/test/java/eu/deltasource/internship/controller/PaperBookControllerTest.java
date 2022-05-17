package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.ActiveUser;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.repository.PaperBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookControllerTest {
    PaperBookController controller = new PaperBookController();
    PaperBookRepository paperBookRepoInstance = PaperBookRepository.getInstance();

    @BeforeAll
    static void login() {
        Credentials creds = new Credentials("keke","keke");
        Name name = new Name("Ivan", "Ivanov", "Nikolov");
        Address address = new Address("Bulgaria", "Sofia", "Bulgaria 123");
        User testUser = new User(name, creds,address, 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);
        ActiveUser.getInstance().setActiveUser(testUser);
    }

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

    @Test
    void testSearchForAPaperBookByAuthorNamesReturnsWantedResult() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByAuthorNames("Vazov");

        // Then
        assertTrue(paperBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAPaperBookByAuthorNamesThatDoesntExistReturnsEmptySet() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByAuthorNames("Kiko");

        // Then
        assertEquals(new HashSet<PaperBook>(), paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByBlankAuthorNamesThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable blankArgument = () -> controller.searchByAuthorNames("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAPaperBookByNullAuthorNamesThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable nullArgument = () -> controller.searchByAuthorNames(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAPaperBookByTitleReturnsWantedResult() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByTitle("Random");

        // Then
        assertTrue(paperBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAPaperBookByTitleThatDoesntExistReturnsEmptySet() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByTitle("Audi");

        // Then
        assertEquals(new HashSet<PaperBook>(), paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByBlankTitleThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable blankArgument = () -> controller.searchByTitle("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAPaperBookByNullTitleThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable nullArgument = () -> controller.searchByTitle(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAPaperBookByIsbnReturnsWantedResult() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        PaperBook paperBooksSearch = controller.searchByIsbn("98-54-895-98");

        // Then
        assertEquals(HarryPotter, paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByIsbnThatDoesntExistReturnsNull() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        PaperBook paperBooksSearch = controller.searchByIsbn("88-7-86");

        // Then
        assertNull(paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByBlankIsbnThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable blankArgument = () -> controller.searchByIsbn("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAPaperBookByNullIsbnThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable nullArgument = () -> controller.searchByIsbn(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAPaperBookByGenreReturnsWantedResult() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByGenre("suspense");

        // Then
        assertTrue(paperBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAPaperBookByGenreThatDoesntExistReturnsEmptySet() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByGenre("mafia");

        // Then
        assertEquals(new HashSet<PaperBook>(), paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByBlankGenreThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable blankArgument = () -> controller.searchByGenre("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAPaperBookByNullGenreThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable nullArgument = () -> controller.searchByGenre(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAPaperBookByTagReturnsWantedResult() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByTag("book");

        // Then
        assertTrue(paperBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAPaperBookByTagThatDoesntExistReturnsEmptySet() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Set<PaperBook> paperBooksSearch = controller.searchByTag("mafia");

        // Then
        assertEquals(new HashSet<PaperBook>(), paperBooksSearch);
    }

    @Test
    void testSearchForAPaperBookByBlankTagThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable blankArgument = () -> controller.searchByTag("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAPaperBookByNullTagThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable nullArgument = () -> controller.searchByTag(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testBorrowABookSuccessfully() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        boolean successfulBorrow = controller.borrow(HarryPotter);

        // Then
        assertTrue(successfulBorrow);
    }


    @Test
    void testBorrowABookThatDoesntExistThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);

        // When
        Executable borrowException = () -> controller.borrow(HarryPotter);

        // Then
        assertThrows(IllegalArgumentException.class, borrowException);
    }

    @Test
    void testReturnABookSuccessfully() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);
        controller.borrow(HarryPotter);

        // When
        boolean successfulReturn = controller.returnBook(HarryPotter);

        // Then
        assertTrue(successfulReturn);
    }

    @Test
    void testReturnABookThatDoesntExistThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);

        // When
        Executable returnException = () -> controller.returnBook(HarryPotter);

        // Then
        assertThrows(IllegalArgumentException.class, returnException);
    }

    @Test
    void testReturnABookThatDoesntWasntBorrowedThrowsAnException() {
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
        PaperBook HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepoInstance.add(HarryPotter);

        // When
        Executable returnException = () -> controller.returnBook(HarryPotter);

        // Then
        assertThrows(IllegalArgumentException.class, returnException);
    }
}