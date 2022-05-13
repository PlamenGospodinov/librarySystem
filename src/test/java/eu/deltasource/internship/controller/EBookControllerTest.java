package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.EBookRepository;
import eu.deltasource.internship.service.EBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
    void testCreateAnEBookSuccessfully() {
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
    void testAddADuplicateEBookReturnsNull() {
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
        controller.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        boolean successfulDelete = controller.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertTrue(successfulDelete);
        assertEquals(0, eBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAnEBookThatDoesntExistReturnsFalse() {
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
    void testDeleteAnEBookByIsbnSuccessfully() {
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
        boolean successfulDelete = controller.deleteByIsbn("98-54-895-98");

        // Then
        assertTrue(successfulDelete);
        assertEquals(0, eBookRepoInstance.getList().size());
    }

    @Test
    void testDeleteAnEBookByInvalidIsbnReturnsFalse() {
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
        boolean successfulDelete = controller.deleteByIsbn("98-74-895-98");

        // Then
        assertFalse(successfulDelete);
        assertEquals(1, eBookRepoInstance.getList().size());
    }

    @Test
    void testReturnAnEBooksSetSuccessfully() {
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
        Set<EBook> eBooksSet = controller.getList();

        // Then
        assertEquals(2, eBooksSet.size());
        assertTrue(eBookRepoInstance.getList().contains(ironHeart));
        assertTrue(eBookRepoInstance.getList().contains(ironHeart2));
    }

    @Test
    void testSearchForAnEBookByAuthorNamesReturnsWantedResult() {
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
        Set<EBook> eBooksSearch = controller.searchByAuthorNames("Vazov");

        // Then
        assertTrue(eBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAnEBookByAuthorNamesThatDoesntExistReturnsEmptySet() {
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
        Set<EBook> eBooksSearch = controller.searchByAuthorNames("Kiko");

        // Then
        assertEquals(new HashSet<EBook>(), eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByBlankAuthorNamesThrowsAnException() {
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
        Executable blankArgument = () -> controller.searchByAuthorNames("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAnEBookByNullAuthorNamesThrowsAnException() {
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
        Executable nullArgument = () -> controller.searchByAuthorNames(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAnEBookByTitleReturnsWantedResult() {
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
        Set<EBook> eBooksSearch = controller.searchByTitle("Random");

        // Then
        assertTrue(eBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAnEBookByTitleThatDoesntExistReturnsEmptySet() {
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
        Set<EBook> eBooksSearch = controller.searchByTitle("Audi");

        // Then
        assertEquals(new HashSet<EBook>(), eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByBlankTitleThrowsAnException() {
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
        Executable blankArgument = () -> controller.searchByTitle("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAnEBookByNullTitleThrowsAnException() {
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
        Executable nullArgument = () -> controller.searchByTitle(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAnEBookByIsbnReturnsWantedResult() {
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
        EBook eBooksSearch = controller.searchByIsbn("98-54-895-98");

        // Then
        assertEquals(HarryPotter, eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByIsbnThatDoesntExistReturnsNull() {
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
        EBook eBooksSearch = controller.searchByIsbn("88-7-86");

        // Then
        assertNull(eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByBlankIsbnThrowsAnException() {
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
        Executable blankArgument = () -> controller.searchByIsbn("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAnEBookByNullIsbnThrowsAnException() {
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
        Executable nullArgument = () -> controller.searchByIsbn(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAnEBookByGenreReturnsWantedResult() {
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
        Set<EBook> eBooksSearch = controller.searchByGenre("suspense");

        // Then
        assertTrue(eBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAnEBookByGenreThatDoesntExistReturnsEmptySet() {
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
        Set<EBook> eBooksSearch = controller.searchByGenre("mafia");

        // Then
        assertEquals(new HashSet<EBook>(), eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByBlankGenreThrowsAnException() {
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
        Executable blankArgument = () -> controller.searchByGenre("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAnEBookByNullGenreThrowsAnException() {
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
        Executable nullArgument = () -> controller.searchByGenre(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }

    @Test
    void testSearchForAnEBookByTagReturnsWantedResult() {
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
        Set<EBook> eBooksSearch = controller.searchByTag("book");

        // Then
        assertTrue(eBooksSearch.contains(HarryPotter));
    }

    @Test
    void testSearchForAnEBookByTagThatDoesntExistReturnsEmptySet() {
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
        Set<EBook> eBooksSearch = controller.searchByTag("mafia");

        // Then
        assertEquals(new HashSet<EBook>(), eBooksSearch);
    }

    @Test
    void testSearchForAnEBookByBlankTagThrowsAnException() {
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
        Executable blankArgument = () -> controller.searchByTag("");

        // Then
        assertThrows(IllegalArgumentException.class, blankArgument);
    }

    @Test
    void testSearchForAnEBookByNullTagThrowsAnException() {
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
        Executable nullArgument = () -> controller.searchByTag(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }
}
