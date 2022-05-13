package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.EBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
        EBook harryPotter0 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertEquals(harryPotter0, harryPotter);
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        EBook harryPotter = service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertNull(harryPotter);
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
        service.create("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // When
        boolean successfulDelete = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

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
        tags.add(Tag.BOOK);

        // When
        boolean successfulRemove = service.delete("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);

        // Then
        assertFalse(successfulRemove);
    }

    @Test
    void testReturnAnEBookSetSuccessfully() {
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
        Set<EBook> eBooks = service.getList();

        //Then
        assertEquals(2, eBooks.size());
        assertTrue(eBooks.contains(harryPotter0));
        assertTrue(eBooks.contains(harryPotter1));
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
        EBook harryPotter0 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.add(harryPotter0);

        // When
        boolean deleteIsSuccessful = service.deleteByIsbn("98-54-895-98");

        //Then
        assertEquals(0, eBookRepoInstance.getList().size());
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
        EBook harryPotter0 = new EBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, "sth", null);
        eBookRepoInstance.add(harryPotter0);

        // When
        boolean deleteIsSuccessful = service.deleteByIsbn("98-55-895-98");

        //Then
        assertEquals(1, eBookRepoInstance.getList().size());
        assertFalse(deleteIsSuccessful);
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
        Set<EBook> eBooksSearch = service.searchByAuthorNames("Vazov");

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
        Set<EBook> eBooksSearch = service.searchByAuthorNames("Kiko");

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
        Executable blankArgument = () -> service.searchByAuthorNames("");

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
        Executable nullArgument = () -> service.searchByAuthorNames(null);

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
        Set<EBook> eBooksSearch = service.searchByTitle("Random");

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
        Set<EBook> eBooksSearch = service.searchByTitle("Audi");

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
        Executable blankArgument = () -> service.searchByTitle("");

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
        Executable nullArgument = () -> service.searchByTitle(null);

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
        EBook eBooksSearch = service.searchByIsbn("98-54-895-98");

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
        EBook eBooksSearch = service.searchByIsbn("88-7-86");

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
        Executable blankArgument = () -> service.searchByIsbn("");

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
        Executable nullArgument = () -> service.searchByIsbn(null);

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
        Set<EBook> eBooksSearch = service.searchByGenre("suspense");

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
        Set<EBook> eBooksSearch = service.searchByGenre("mafia");

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
        Executable blankArgument = () -> service.searchByGenre("");

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
        Executable nullArgument = () -> service.searchByGenre(null);

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
        Set<EBook> eBooksSearch = service.searchByTag("book");

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
        Set<EBook> eBooksSearch = service.searchByTag("mafia");

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
        Executable blankArgument = () -> service.searchByTag("");

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
        Executable nullArgument = () -> service.searchByTag(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullArgument);
    }
}