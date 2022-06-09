package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookRepositoryTest {

    PaperBookRepository paperBookRepoInstance = PaperBookRepository.getInstance();

    @AfterEach
    void clearAuthorRepo() {
        paperBookRepoInstance.clearRepository();
    }

    @Test
    void testAddAPaperBookSuccessfully() {
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
        PaperBook successfulAdd = paperBookRepoInstance.add(HarryPotter);

        // Then
        assertEquals(1, paperBookRepoInstance.getList().size());
        assertTrue(paperBookRepoInstance.getList().contains(successfulAdd));
    }

    @Test
    void testAddAPaperBookDuplicateReturnsFalse() {
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
        PaperBook failedAdd = paperBookRepoInstance.add(HarryPotter);

        // Then
        assertEquals(1, paperBookRepoInstance.getList().size());
        assertNull(failedAdd);
    }

    @Test
    void testRemoveAPaperBookSuccessfully() {
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
        paperBookRepoInstance.remove(HarryPotter);

        // Then
        assertEquals(0, paperBookRepoInstance.getList().size());
    }

    @Test
    void testRemoveAPaperBookByIsbnSuccessfully() {
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
        paperBookRepoInstance.removeByIsbn("98-54-895-98");

        // Then
        assertEquals(0, paperBookRepoInstance.getList().size());
    }

    @Test
    void testRemoveAPaperBookByIsbnThatDoesntExistReturnsFalse() {
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
        boolean removeResult = paperBookRepoInstance.removeByIsbn("98-54-897-98");

        // Then
        assertFalse(removeResult);
        assertEquals(1, paperBookRepoInstance.getList().size());
    }

    @Test
    void testGetAPaperBookListSuccessfully() {
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
        Set<PaperBook> paperBooks = paperBookRepoInstance.getList();

        // Then
        assertTrue(paperBooks.contains(HarryPotter));
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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByAuthorsName("Vazov");

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByAuthorsName("Kiko");

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
        Executable blankArgument = () -> paperBookRepoInstance.searchByAuthorsName("");

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
        Executable nullArgument = () -> paperBookRepoInstance.searchByAuthorsName(null);

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByTitle("Random");

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByTitle("Audi");

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
        Executable blankArgument = () -> paperBookRepoInstance.searchByTitle("");

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
        Executable nullArgument = () -> paperBookRepoInstance.searchByTitle(null);

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
        PaperBook paperBooksSearch = paperBookRepoInstance.searchByIsbn("98-54-895-98");

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
        PaperBook paperBooksSearch = paperBookRepoInstance.searchByIsbn("88-7-86");

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
        Executable blankArgument = () -> paperBookRepoInstance.searchByIsbn("");

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
        Executable nullArgument = () -> paperBookRepoInstance.searchByIsbn(null);

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByGenre("suspense");

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByGenre("mafia");

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
        Executable blankArgument = () -> paperBookRepoInstance.searchByGenre("");

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
        Executable nullArgument = () -> paperBookRepoInstance.searchByGenre(null);

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByTag("book");

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
        Set<PaperBook> paperBooksSearch = paperBookRepoInstance.searchByTag("mafia");

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
        Executable blankArgument = () -> paperBookRepoInstance.searchByTag("");

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
        Executable nullArgument = () -> paperBookRepoInstance.searchByTag(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }
}