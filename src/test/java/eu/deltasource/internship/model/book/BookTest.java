package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testShouldCreateBookSuccessfully() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);
        EBook HarryPotter = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);

        // When
        EBook HarryPotter2 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);

        // Then
        assertEquals(HarryPotter, HarryPotter2);
    }

    @Test
    void testConstructorThrowsAnExceptionIfTitleIsNull() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable titleSetterException = () -> new EBook(null, authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, titleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfTitleIsEmpty() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable titleSetterException = () -> new EBook("", authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);


        // Then
        assertThrows(IllegalArgumentException.class, titleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAuthorsListIsNull() {
        // Given
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable authorsSetterException = () -> new EBook("Harry Potter", null, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);


        // Then
        assertThrows(IllegalArgumentException.class, authorsSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfGenresListIsNull() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        tags.add(Tag.POPULAR);

        // When
        Executable genresSetterException = () -> new EBook("Harry Potter", authors, null, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, genresSetterException);
    }

    @Test
    void setSummaryToNullThrowsAnException() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable summarySetterException = () -> new EBook("Harry Potter", authors, genres, null, "15-9-8-45", tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, summarySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSummaryIsEmpty() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable summarySetterException = () -> new EBook("Harry Potter", authors, genres, "", "15-9-8-45", tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, summarySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfISBNIsEmpty() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable isbnSetterException = () -> new EBook("Harry Potter", authors, genres, "Brief summary", "", tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, isbnSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfISBNIsNull() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable isbnSetterException = () -> new EBook("Harry Potter", authors, genres, "", null, tags, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, isbnSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfTagsListIsNull() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);

        // When
        Executable tagsSetterException = () -> new EBook("Harry Potter", authors, genres, "", "25-69-582", null, "https://somelink.com", null);

        // Then
        assertThrows(IllegalArgumentException.class, tagsSetterException);
    }
}