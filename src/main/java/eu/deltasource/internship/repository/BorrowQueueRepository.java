package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.BorrowQueue;
import eu.deltasource.internship.model.book.PaperBook;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *  Repository for all the book borrow queues
 */
public class BorrowQueueRepository {

    private static BorrowQueueRepository INSTANCE;

    private final Set<BorrowQueue> borrowQueues;

    private BorrowQueueRepository() {
        this.borrowQueues = new HashSet<>();
    }

    public static BorrowQueueRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BorrowQueueRepository();
        }
        return INSTANCE;
    }

    /**
     * Adds a queue to the repository
     *
     * @param queue - the borrow queue we want to add to the repository
     * @return true if the add is successful
     */
    public boolean addQueue(BorrowQueue queue) {
        if (queue == null) {
            throw new IllegalArgumentException("Invalid queue!");
        }

        return borrowQueues.add(queue);
    }

    /**
     * Determines whether a queue of a given book is locked now for the first user in line.
     *
     * @param book - The book we use to search
     * @return true if the queue is locked and false if it isn't
     */
    public boolean isQueueLocked(PaperBook book) {
        if (book == null) {
            throw new IllegalArgumentException("Book parameter cannot be null to complete operation.");
        }

        BorrowQueue queue = this.getBorrowQueueByBook(book);
        if (queue == null) {
            throw new IllegalArgumentException("This book has no queue");
        }

        return queue.getFinalTakeDay().isAfter(LocalDate.now());
    }

    /**
     * Gets a queue of a given book
     *
     * @param book - The book which we use as search criteria
     * @return The BorrowQueue of the given book or null if there isn't such queue
     */
    public BorrowQueue getBorrowQueueByBook(PaperBook book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!.");
        }

        for(BorrowQueue queue : borrowQueues) {
            if(queue.getBook().equals(book)) {
                return queue;
            }
        }
        return null;
    }

    /**
     *
     * @return The whole set of borrow queues
     */
    public Set<BorrowQueue> getBorrowQueues() {
        return borrowQueues;
    }

    public void clearRepository() {
        borrowQueues.clear();
    }
}
