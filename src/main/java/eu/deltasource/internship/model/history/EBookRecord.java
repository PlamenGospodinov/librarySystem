package eu.deltasource.internship.model.history;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Status;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Stores information about an EBook record - logDate(date of certain activity) and status(READ or DOWNLOADED)
 */
public class EBookRecord {

    private EBook eBook;

    private LocalDate logDate;

    private Status status;

    /**
     * Constructor for the EBookHistory class
     *
     * @param status    EBook's status
     * @param eBook     The EBook we put in the history
     * @param logDate   A log date for the current EBook we enter
     */
    EBookRecord(Status status, EBook eBook, LocalDate logDate) {
        setStatus(status);
        setBook(eBook);
        setLogDate(logDate);
    }

    public Status getStatus() {
        return this.status;
    }

    private void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status of history entry cannot be set to null.");
        }
        this.status = status;
    }

    public EBook getBook() {
        return this.eBook;
    }

    private void setBook(EBook book) {
        if (book == null) {
            throw new SetterValidationException("eBook");
        }
        eBook = book;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    private void setLogDate(LocalDate logDate) {
        if (logDate == null) {
            throw new SetterValidationException("log date");
        }
        this.logDate = logDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBookRecord that = (EBookRecord) o;
        return eBook.equals(that.eBook) && logDate.equals(that.logDate) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eBook, logDate, status);
    }
}
