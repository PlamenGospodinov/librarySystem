package eu.deltasource.internship.model.book;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.user.User;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class BorrowQueue {

    private PaperBook book;

    private Queue<User> queue;

    private LocalDate finalTakeDay;

    public BorrowQueue(PaperBook book) {
        setBook(book);
        setFinalTakeDay();
        queue = new LinkedList<>();
    }

    private boolean isQueueLocked() {
        return finalTakeDay.isBefore(LocalDate.now());
    }

    private void addUserInQueue(User user) {
        if(user == null) {
            throw new SetterValidationException("user");
        }
        if(queue.isEmpty()) {
            setFinalTakeDay();
        }
        queue.add(user);
    }

    private User moveQueue() {
        if(queue.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty and can't be moved!");
        }

        if(queue.poll() != null) {
            setFinalTakeDay();
            return queue.peek();
        }
        return null;
    }

    public Queue<User> getUsersInQueue() {
        return queue;
    }

    private void setBook(PaperBook book) {
        if(book == null) {
            throw new SetterValidationException("book");
        }
        this.book = book;
    }

    private void setFinalTakeDay() {
        finalTakeDay = LocalDate.now().plusDays(3);
    }
}
