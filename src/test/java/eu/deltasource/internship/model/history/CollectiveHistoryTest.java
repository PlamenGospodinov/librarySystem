package eu.deltasource.internship.model.history;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.*;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectiveHistoryTest {

    @Test
    void testCreateCollectiveHistoryWithNullUserThrowsAnException() {
        // Given

        // When
        Executable nullUserException = () -> new CollectiveHistory(null);

        // Then
        assertThrows(IllegalArgumentException.class, nullUserException);
    }

    @Test
    void testCreateCollectiveHistorySuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);

        // When
        CollectiveHistory history = new CollectiveHistory(user);

        // Then
        assertEquals(user, history.getUser());
    }

    @Test
    void testBorrowABookSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        boolean successfulBorrow = history.borrowABook(HarryPotter1);

        // Then
        assertTrue(successfulBorrow);
    }

    @Test
    void testBorrowANullBookThrowsAnException() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);

        // When
        Executable borrowException = () -> history.borrowABook(null);

        // Then
        assertThrows(IllegalArgumentException.class, borrowException);
    }

    @Test
    void testBorrowAnAlreadyBorrowedBookThrowsAnException() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        history.borrowABook(HarryPotter1);

        // When
        Executable borrowException = () -> history.borrowABook(HarryPotter1);

        // Then
        assertThrows(IllegalArgumentException.class, borrowException);
    }

    @Test
    void testReturnABookSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        history.borrowABook(HarryPotter1);

        // When
        boolean successfulReturn = history.returnABook(HarryPotter1);

        // Then
        assertTrue(successfulReturn);
    }

    @Test
    void testReturnABookThatWasntBorrowedThrowsAnExceptionSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        Executable returnException = () -> history.returnABook(HarryPotter1);

        // Then
        assertThrows(IllegalArgumentException.class, returnException);
    }

    @Test
    void testReturnANullBookThrowsAnExceptionSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);

        // When
        Executable returnException = () -> history.returnABook(null);

        // Then
        assertThrows(IllegalArgumentException.class, returnException);
    }

    @Test
    void testFindBorrowedBookReturnsNullIfItDoesntFindAnything() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        BorrowedBookRecord borrowedBookRecord =  history.findBorrowedBook(HarryPotter1);

        // Then
        assertNull(borrowedBookRecord);
    }

    @Test
    void testFindBorrowedBookReturnsBorrowedBookRecordIfItFindsIt() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        history.borrowABook(HarryPotter1);

        // When
        BorrowedBookRecord borrowedBookRecord =  history.findBorrowedBook(HarryPotter1);

        // Then
        assertEquals(HarryPotter1, borrowedBookRecord.getBorrowedBook());
    }

    @Test
    void testAddToEBookRecordsSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        EBookRecord eBookRecord =  new EBookRecord(Status.READ, HarryPotter1, LocalDate.now());

        // When
        boolean successfulAdd = history.addToEBookRecords(HarryPotter1, Status.READ);

        // Then
        assertTrue(successfulAdd);
        assertTrue(history.getEBookRecords().contains(eBookRecord));
    }

    @Test
    void testAddToEBookRecordsThrowsAnExceptionIfEBookIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);

        // When
        Executable addException = () -> history.addToEBookRecords(null, Status.READ);

        // Then
        assertThrows(IllegalArgumentException.class, addException);
    }

    @Test
    void testAddToEBookRecordsThrowsAnExceptionIfStatusIsDifferentThanReadOrDownload() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User user = new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"heheh",true);
        CollectiveHistory history = new CollectiveHistory(user);
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
        Executable addException = () -> history.addToEBookRecords(HarryPotter1, Status.BORROWED);

        // Then
        assertThrows(IllegalArgumentException.class, addException);
    }
}