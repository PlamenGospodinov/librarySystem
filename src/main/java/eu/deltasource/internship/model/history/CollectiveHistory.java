package eu.deltasource.internship.model.history;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Status;
import eu.deltasource.internship.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores all the history of user's activity - READ or DOWNLOADED EBooks as well as BORROWED or RETURNED Paper Books
 */
public class CollectiveHistory {

    private User user;

    private List<EBookRecord> eBookRecords;

    private Map<BorrowedBookRecord, Status> paperBookRecords;

    public CollectiveHistory(User user) {
        setUser(user);
        eBookRecords = new ArrayList<>();
        paperBookRecords = new HashMap<>();
    }

    /**
     * Adds a PaperBook to the paperBookRecords with the status of BORROWED
     * @param book - the book we want to borrow
     * @return true if we successfully borrowed the book
     */
    public boolean borrowABook(PaperBook book) {
        if (book == null) {
            throw new SetterValidationException("book");
        }

        if (findBorrowedBook(book) != null) {
            throw new IllegalArgumentException("The book is already borrowed!");
        }

        BorrowedBookRecord newRecord = new BorrowedBookRecord(book);
        paperBookRecords.put(newRecord, Status.BORROWED);
        return true;
    }

    /**
     * Changes a PaperBook's status to RETURNED if it exists in the paperBookRecords
     * @param book - the book we want to return
     * @return true if we successfully returned the book
     */
    public boolean returnABook(PaperBook book) {
        if (book == null) {
            throw new SetterValidationException("book");
        }

        if (findBorrowedBook(book) == null) {
            throw new IllegalArgumentException("You can't return a book you haven't borrowed!");
        }

        BorrowedBookRecord borrowedBook = findBorrowedBook(book);
        paperBookRecords.put(borrowedBook, Status.RETURNED);
        return true;
    }

    /**
     * Finds whether a book exists in the paperBookRecords and whether it is with a status of BORROWED
     * @param book - the book we want to find
     * @return BorrowedBookRecord which we can use in the returnABook method
     */
    public BorrowedBookRecord findBorrowedBook(PaperBook book) {
        for (BorrowedBookRecord bookRecord : paperBookRecords.keySet()) {
            if (bookRecord.getBorrowedBook() == book) {
                if (paperBookRecords.get(bookRecord) == Status.BORROWED) {
                    return bookRecord;
                }
            }
        }
        return null;
    }

    /**
     * Adds records to the eBookRecords list which is part of the user's history
     * @param book
     * @param status
     * @return
     */
    public boolean addToEBookRecords(EBook book, Status status) {
        if (book == null) {
            throw new SetterValidationException("book");
        }

        if (status != Status.DOWNLOADED && status != Status.READ) {
            throw new SetterValidationException("status");
        }

        EBookRecord bookRecord = new EBookRecord(status, book, LocalDate.now());
        eBookRecords.add(bookRecord);
        return true;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        if(user == null) {
            throw new SetterValidationException("user");
        }
        this.user = user;
    }

    public List<EBookRecord> getEBookRecords() {
        return eBookRecords;
    }

    public Map<BorrowedBookRecord, Status> getPaperBookRecords() {
        return paperBookRecords;
    }
}
