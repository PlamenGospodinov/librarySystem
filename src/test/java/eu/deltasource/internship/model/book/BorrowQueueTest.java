package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BorrowQueueTest {

    static PaperBook HarryPotter;

    @BeforeAll
    static void initializeTestData() {
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Author IvanVazov = new Author(name, "Bulgaria", LocalDate.of(1850, 7, 9), LocalDate.of(1921, 9, 22));
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        genres.add(Genre.SUSPENSE);
        genres.add(Genre.DETECTIVE);
        authors.add(IvanVazov);
        tags.add(Tag.BOOK);
        HarryPotter = new PaperBook("RandomName", authors, genres, "Sth small", "98-54-895-98", tags, 2);
    }

    @Test
    void testAddUserInQueueSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        BorrowQueue queue = new BorrowQueue(HarryPotter);

        // When
        queue.addUserInQueue(Ivan);

        // Then
        assertTrue(queue.getUsersInQueue().contains(Ivan));
    }

    @Test
    void testCreateWithNullBookThrownsAnException() {
        // Given

        // When
        Executable createException = () -> new BorrowQueue(null);

        // Then
        assertThrows(IllegalArgumentException.class, createException);
    }

    @Test
    void testAddNullUserToQueueThrowsAnException() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);

        // When
        Executable addException = () -> queue.addUserInQueue(null);

        // Then
        assertThrows(IllegalArgumentException.class, addException);
    }

    @Test
    void testIfQueueIsLockedReturnsTrueIfUserIsPassed() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        queue.addUserInQueue(Ivan);

        // When
        boolean isItLocked =  queue.isQueueLocked();

        // Then
        assertTrue(isItLocked);
    }

    void testIfQueueIsLockedReturnsFalseIfTheQueueIsntLocked() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);

        // When
        boolean isItLocked =  queue.isQueueLocked();

        // Then
        assertFalse(isItLocked);
    }

    @Test
    void testMoveQueueThrowsAnExceptionIfTheQueueIsEmpty() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);

        // When
        Executable queueMoveException = () -> queue.moveQueue();

        // Then
        assertThrows(IllegalArgumentException.class, queueMoveException);
    }

    @Test
    void testReturnCurrentUser() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        Name name2 = new Name("Simo", "Minchov", "Vazov");
        Credentials credentials2 = new Credentials("pesho123", "efs8efs494");
        Address address2 = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Simo = new User(name2, credentials2, address2, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        queue.addUserInQueue(Ivan);
        queue.addUserInQueue(Simo);

        // When
        User currentUser =  queue.returnCurrentUser();

        // Then
        assertEquals(Ivan, currentUser);
    }

    @Test
    void testGetQueueBook() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);

        // When
        PaperBook queueBook =  queue.getBook();

        // Then
        assertEquals(HarryPotter, queueBook);
    }

    @Test
    void getUsersInQueue() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        Name name2 = new Name("Simo", "Minchov", "Vazov");
        Credentials credentials2 = new Credentials("pesho123", "efs8efs494");
        Address address2 = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Simo = new User(name2, credentials2, address2, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        queue.addUserInQueue(Ivan);
        queue.addUserInQueue(Simo);

        // When
        Queue<User> queueUsers =  queue.getUsersInQueue();

        // Then
        assertTrue(queueUsers.contains(Ivan));
        assertTrue(queueUsers.contains(Simo));
    }

    @Test
    void getFinalTakeDay() {
        // Given
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Ivan = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        queue.addUserInQueue(Ivan);

        // When
        LocalDate takeDay =  queue.getFinalTakeDay();

        // Then
        assertEquals(LocalDate.now().plusDays(3), takeDay);
    }
}