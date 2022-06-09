package eu.deltasource.internship.model.history;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.book.PaperBook;
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

class BorrowedBookRecordTest {

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
        PaperBook HarryPotter1 = new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 5);

        // When
        BorrowedBookRecord bookRecord = new BorrowedBookRecord(HarryPotter1);

        // Then
        assertEquals(HarryPotter1, bookRecord.getBorrowedBook());
        assertEquals(LocalDate.now(), bookRecord.getBorrowDate());
        assertEquals(LocalDate.now().plusDays(14), bookRecord.getReturnDate());
    }

    @Test
    void testCreateEBookRecordWithNullBookThrowsAnException() {
        // Given

        // When
        Executable nullBookException = () -> new BorrowedBookRecord(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullBookException);
    }

    @Test
    void testPostponeSuccessfully() {
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
        PaperBook HarryPotter1 = new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 5);
        BorrowedBookRecord bookRecord = new BorrowedBookRecord(HarryPotter1);

        // When
        boolean successfullyPostpone = bookRecord.postpone(5);

        // Then
        assertEquals(LocalDate.now().plusDays(19), bookRecord.getReturnDate());
        assertTrue(successfullyPostpone);
    }

    @Test
    void testPostponeWithInvalidDaysThrowsAnException() {
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
        PaperBook HarryPotter1 = new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 5);
        BorrowedBookRecord bookRecord = new BorrowedBookRecord(HarryPotter1);

        // When
        Executable postponeException = () -> bookRecord.postpone(50);

        // Then
        assertThrows(IllegalArgumentException.class, postponeException);
    }

    @Test
    void testPostponeReturnsFalseIfWeAlreadyUsed14AllowedDays() {
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
        PaperBook HarryPotter1 = new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 5);
        BorrowedBookRecord bookRecord = new BorrowedBookRecord(HarryPotter1);
        bookRecord.postpone(12);

        // When
        boolean notSuccessfulPostpone = bookRecord.postpone(5);

        // Then
        assertFalse(notSuccessfulPostpone);
    }
}