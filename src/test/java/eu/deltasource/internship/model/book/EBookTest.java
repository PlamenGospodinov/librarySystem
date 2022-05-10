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

class EBookTest {

    @Test
    void testShouldCreateEBookSuccessfully() {
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
        EBook HarryPotter1 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://blablabal.bg", null);

        // When
        EBook HarryPotter2 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://blablabal.bg", null);

        // Then
        assertEquals(HarryPotter1, HarryPotter2);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLinkForReadingIsNull() {
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
        Executable nullLinkForReadingSetterException = () -> new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, null, null);

        // Then
        assertThrows(IllegalArgumentException.class, nullLinkForReadingSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLinkForReadingIsEmpty() {
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
        Executable emptyLinkForReadingSetterException = () -> new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "", null);

        // Then
        assertThrows(IllegalArgumentException.class, emptyLinkForReadingSetterException);
    }
}