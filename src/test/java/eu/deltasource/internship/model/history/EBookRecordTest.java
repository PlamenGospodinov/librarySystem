package eu.deltasource.internship.model.history;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Status;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EBookRecordTest {

    @Test
    void testCreateEBookRecordSuccessfully() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date.plusYears(80));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);
        EBook HarryPotter1 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://blablabal.bg", null);

        // When
        EBookRecord bookRecord = new EBookRecord(Status.READ, HarryPotter1, LocalDate.now());

        // Then
        assertEquals(HarryPotter1, bookRecord.getBook());
        assertEquals(LocalDate.now(), bookRecord.getLogDate());
        assertEquals(Status.READ, bookRecord.getStatus());
    }

    @Test
    void testCreateEBookRecordWithNullBookThrowsAnException() {
        // Given

        // When
        Executable nullBookException = () -> new EBookRecord(Status.READ, null, LocalDate.now());

        // Then
        assertThrows(IllegalArgumentException.class, nullBookException);
    }

    @Test
    void testCreateEBookRecordWithNullStatusThrowsAnException() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date.plusYears(80));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);
        EBook HarryPotter1 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://blablabal.bg", null);

        // When
        Executable nullStatusException = () -> new EBookRecord(null, HarryPotter1, LocalDate.now());

        // Then
        assertThrows(IllegalArgumentException.class, nullStatusException);
    }

    @Test
    void testCreateEBookRecordWithNullLogDateThrowsAnException() {
        // Given
        Name name = new Name("Gosho", "Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date.plusYears(80));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add(Tag.POPULAR);
        EBook HarryPotter1 = new EBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, "https://blablabal.bg", null);

        // When
        Executable nullLogDateException = () -> new EBookRecord(Status.READ, HarryPotter1, null);

        // Then
        assertThrows(IllegalArgumentException.class, nullLogDateException);
    }
}