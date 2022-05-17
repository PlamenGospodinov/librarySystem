package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.BorrowQueue;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BorrowQueueRepositoryTest {

    BorrowQueueRepository borrowQueueRepository = BorrowQueueRepository.getInstance();
    PaperBookRepository paperBookRepository = PaperBookRepository.getInstance();

    @AfterEach
    void clear() {
        borrowQueueRepository.clearRepository();
        paperBookRepository.clearRepository();
    }

    @Test
    void testAddQueueSuccessfully() {
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
        paperBookRepository.add(HarryPotter);
        BorrowQueue queue = new BorrowQueue(HarryPotter);

        // When
        boolean successfulAdd = borrowQueueRepository.addQueue(queue);

        // Then
        assertTrue(successfulAdd);
        assertTrue(borrowQueueRepository.getBorrowQueues().contains(queue));
        assertEquals(1, borrowQueueRepository.getBorrowQueues().size());
    }

    @Test
    void testAddQueueThatIsNullThrowsAnException() {
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
        paperBookRepository.add(HarryPotter);
        BorrowQueue queue = null;

        // When
        Executable addQueueException = () -> borrowQueueRepository.addQueue(queue);

        // Then
        assertThrows(IllegalArgumentException.class, addQueueException);
    }

    @Test
    void testGetBorrowQueueByBookThrowsAnExceptionIfBookIsNull() {
        // Given

        // When
        Executable getQueueByBookException = () -> borrowQueueRepository.getBorrowQueueByBook(null);

        // Then
        assertThrows(IllegalArgumentException.class, getQueueByBookException);
    }

    @Test
    void testGetBorrowQueueByBookReturnsNullIfSuchBookQueueDoesntExist() {
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
        paperBookRepository.add(HarryPotter);

        // When
        BorrowQueue nullQueue = borrowQueueRepository.getBorrowQueueByBook(HarryPotter);

        // Then
        assertNull(nullQueue);
    }

    @Test
    void testGetBorrowQueueByBookReturnsBorrowQueueSuccessfully() {
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
        paperBookRepository.add(HarryPotter);
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        borrowQueueRepository.addQueue(queue);

        // When
        BorrowQueue successfullyAddedQueue = borrowQueueRepository.getBorrowQueueByBook(HarryPotter);

        // Then
        assertEquals(queue, successfullyAddedQueue);
    }

    @Test
    void testGetBorrowQueuesSuccessfully() {
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
        PaperBook HarryPotter2 = new PaperBook("RandomName5", authors, genres, "Sth small", "98-54-895-98", tags, 2);
        paperBookRepository.add(HarryPotter);
        paperBookRepository.add(HarryPotter2);
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        borrowQueueRepository.addQueue(queue);
        BorrowQueue queue2 = new BorrowQueue(HarryPotter2);
        borrowQueueRepository.addQueue(queue2);

        // When
        Set<BorrowQueue> borrowQueues = borrowQueueRepository.getBorrowQueues();

        // Then
        assertTrue(borrowQueues.contains(queue));
        assertTrue(borrowQueues.contains(queue2));
    }

    @Test
    void testIsQueueLockedThrowsAnExceptionIfBookIsNull() {
        // Given

        // When
        Executable isLockedException = () -> borrowQueueRepository.isQueueLocked(null);

        // Then
        assertThrows(IllegalArgumentException.class, isLockedException);
    }

    @Test
    void testIsQueueLockedThrowsAnExceptionIfQueueIsNull() {
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
        paperBookRepository.add(HarryPotter);

        // When
        Executable isLockedException = () -> borrowQueueRepository.isQueueLocked(HarryPotter);

        // Then
        assertThrows(IllegalArgumentException.class, isLockedException);
    }

    @Test
    void testIsQueueLockedReturnsTrueIfItIsLocked() {
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
        paperBookRepository.add(HarryPotter);
        BorrowQueue queue = new BorrowQueue(HarryPotter);
        borrowQueueRepository.addQueue(queue);

        // When
        boolean isLocked = borrowQueueRepository.isQueueLocked(HarryPotter);

        // Then
        assertTrue(isLocked);
    }
}