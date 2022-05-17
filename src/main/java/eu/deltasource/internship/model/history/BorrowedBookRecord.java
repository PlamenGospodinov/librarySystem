package eu.deltasource.internship.model.history;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.book.PaperBook;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Stores information of a book record
 */
public class BorrowedBookRecord {

    private PaperBook borrowedBook;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private int postponementDays;

    public BorrowedBookRecord(PaperBook borrowedBook) {
        setBorrowedBook(borrowedBook);
        setBorrowDate();
        setReturnDate();
        postponementDays = 0;
    }

    /**
     * Specialised method used for postponement
     * @param days - the amount of days we want to postpone with
     * @return true if we can postpone with the given days
     */
    public boolean postpone(int days) {
        if(days <= 0 || days >= 14) {
            throw new SetterValidationException("days");
        }
        if(getPostponementDays() + days > 14) {
            System.out.println("You have already postponed for " + getPostponementDays() + ".The allowed postponement is only 14 days!");
            return false;
        }
        postponementDays += days;
        returnDate = returnDate.plusDays(days);
        return true;
    }

    public PaperBook getBorrowedBook() {
        return borrowedBook;
    }

    private int getPostponementDays() {
        return postponementDays;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    private void setBorrowedBook(PaperBook borrowedBook) {
        if(borrowedBook == null) {
            throw new SetterValidationException("book");
        }
        this.borrowedBook = borrowedBook;
    }

    private void setBorrowDate() {
        borrowDate = LocalDate.now();
    }

    /**
     * Sets the return date 14 days from the borrow date as each user is able to borrow a book for 14 days
     */
    private void setReturnDate() {
        this.returnDate = borrowDate.plusDays(14);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBookRecord that = (BorrowedBookRecord) o;
        return borrowedBook.equals(that.borrowedBook) && borrowDate.equals(that.borrowDate) && returnDate.equals(that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowedBook, borrowDate, returnDate);
    }
}
