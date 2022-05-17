package eu.deltasource.internship.model.book;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.user.User;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Specialised class for the borrow queue
 */
public class BorrowQueue {

    private PaperBook book;

    private Queue<User> queue;

    private LocalDate finalTakeDay;

    public BorrowQueue(PaperBook book) {
        setBook(book);
        setFinalTakeDay();
        queue = new LinkedList<>();
    }

    /**
     * Checks whether the queue is locked or not or in other words whether user's 3 day period of borrowing the book expired.
     * @return Boolean which determines if the queue is locked
     */
    public boolean isQueueLocked() {
        return finalTakeDay.isAfter(LocalDate.now());
    }

    /**
     * Adds the user in the queue if the queue is not empty. Otherwise the 3-day count begins.
     * @param user The user added to the queue
     */
    public void addUserInQueue(User user) {
        if(user == null) {
            throw new SetterValidationException("user");
        }
        if(queue.isEmpty()) {
            setFinalTakeDay();
        }
        queue.add(user);
    }

    /**
     * Moves the queue so that the next user in line becomes the current user who can borrow the book.
     * @return The user which the book is now available for.
     */
    public User moveQueue() {
        if(queue.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty and can't be moved!");
        }

        if(queue.poll() != null) {
            setFinalTakeDay();
            return queue.peek();
        }
        return null;
    }

    /**
     * Returns the user who is currently borrowing the book.
     * @return User who is now in turn to borrow the book
     */
    public User returnCurrentUser() {
        if(queue.isEmpty()) {
            throw new IllegalArgumentException("Can't return current user as the queue is empty!");
        }
        return queue.peek();
    }

    public PaperBook getBook() {
        return book;
    }

    /**
     * Gets the whole queue of users waiting for the book
     * @return Queue of users
     */
    public Queue<User> getUsersInQueue() {
        return queue;
    }

    private void setBook(PaperBook book) {
        if(book == null) {
            throw new SetterValidationException("book");
        }
        this.book = book;
    }

    public LocalDate getFinalTakeDay() {
        return finalTakeDay;
    }

    private void setFinalTakeDay() {
        finalTakeDay = LocalDate.now().plusDays(3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowQueue that = (BorrowQueue) o;
        return book.equals(that.book) && queue.equals(that.queue) && finalTakeDay.equals(that.finalTakeDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, queue, finalTakeDay);
    }
}
