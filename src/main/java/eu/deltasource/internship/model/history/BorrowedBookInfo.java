package eu.deltasource.internship.model.history;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.book.PaperBook;

import java.time.LocalDate;
import java.util.Objects;

public class BorrowedBookInfo {

    private PaperBook borrowedBook;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private int postponementDays;

    public BorrowedBookInfo(PaperBook borrowedBook, LocalDate borrowDate) {
        setBorrowedBook(borrowedBook);
        setBorrowDate(borrowDate);
        setReturnDate();
        setPostponementDays(0);
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
        setPostponementDays(getPostponementDays() + days);
        setNewReturnDate(days);
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

    public void setPostponementDays(int days) {
        postponementDays = days;
    }

    public void setBorrowedBook(PaperBook borrowedBook) {
        if(borrowedBook == null) {
            throw new SetterValidationException("book");
        }
        this.borrowedBook = borrowedBook;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        if(borrowDate == null) {
            throw new SetterValidationException("borrow");
        }
        this.borrowDate = borrowDate;
    }

    public void setReturnDate() {
        this.returnDate = borrowDate.plusDays(14);
    }

    public void setNewReturnDate(int days) {
        returnDate = returnDate.plusDays(days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBookInfo that = (BorrowedBookInfo) o;
        return borrowedBook.equals(that.borrowedBook) && borrowDate.equals(that.borrowDate) && returnDate.equals(that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowedBook, borrowDate, returnDate);
    }
}
