package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.BorrowQueue;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.history.BorrowedBookRecord;
import eu.deltasource.internship.model.history.CollectiveHistory;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.repository.BorrowQueueRepository;
import eu.deltasource.internship.repository.CollectiveHistoryRepository;
import eu.deltasource.internship.repository.PaperBookRepository;

public class BorrowService {

    private static BorrowService INSTANCE = new BorrowService();

    private final PaperBookRepository bookRepository = PaperBookRepository.getInstance();

    private final BorrowQueueRepository queueRepository = BorrowQueueRepository.getInstance();

    private final CollectiveHistoryRepository history = CollectiveHistoryRepository.getInstance();

    private BorrowService() {
    }

    public static BorrowService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BorrowService();
        }
        return INSTANCE;
    }

    public boolean borrowABook(User user, PaperBook book) {
        if (user == null) {
            throw new IllegalArgumentException("Null user cannot borrow a book!");
        }

        if (bookRepository.getList().contains(book) == false) {
            throw new IllegalArgumentException("User cannot borrow a non-existent book!");
        }

        for (PaperBook paperBook : bookRepository.getList()) {
            if (paperBook.equals(book)) {
                BorrowQueue queue = queueRepository.getBorrowQueueByBook(book);
                CollectiveHistory collectiveHistory = history.getCollectiveHistoryByUser(user);
                if (paperBook.getAvailableCopies() == 0) {
                    if (queue == null) {
                        queue = new BorrowQueue(book);
                        queue.addUserInQueue(user);
                        queueRepository.addQueue(queue);
                    } else {
                        if (queue.getUsersInQueue().contains(user)) {
                            throw new IllegalArgumentException("User is already in the queue for the given book.");
                        }

                        if (queue.returnCurrentUser() != null && !queue.returnCurrentUser().equals(user) && queue.isQueueLocked()) {
                            throw new IllegalArgumentException("The queue is locked for another user.");
                        }

                        queue.addUserInQueue(user);
                    }

                    if (collectiveHistory == null) {
                        collectiveHistory = new CollectiveHistory(user);
                        history.addCollectiveHistory(collectiveHistory);
                    }

                    BorrowedBookRecord borrowedBookRecord = collectiveHistory.findBorrowedBook(book);
                    if (borrowedBookRecord != null) {
                        throw new IllegalArgumentException("The book is already borrowed!");
                    }

                    collectiveHistory.borrowABook(book);
                } else {

                    if (collectiveHistory == null) {
                        collectiveHistory = new CollectiveHistory(user);
                        history.addCollectiveHistory(collectiveHistory);
                    }

                    BorrowedBookRecord borrowedBookRecord = collectiveHistory.findBorrowedBook(book);
                    if (borrowedBookRecord != null) {
                        throw new IllegalArgumentException("The book is already borrowed!");
                    }
                    collectiveHistory.borrowABook(book);
                    book.setAvailableCopies(book.getAvailableCopies()-1);
                }
            }
        }
        return true;
    }

    public boolean returnABook(User user, PaperBook book) {
        if (user == null) {
            throw new IllegalArgumentException("Null user cannot return a book!");
        }

        if(!bookRepository.getList().contains(book)) {
            throw new IllegalArgumentException("Cannot return a book that is not present in the book repository!");
        }

        for (CollectiveHistory collectiveHistory : history.getCollectiveHistorySet()) {
            if (collectiveHistory.getUser().equals(user)) {
                BorrowedBookRecord borrowedBookRecord = collectiveHistory.findBorrowedBook(book);
                if (borrowedBookRecord == null) {
                    throw new IllegalArgumentException("No information for this book's borrow for this user, so you can't return something you haven't borrowed!");
                }

                collectiveHistory.returnABook(book);
                BorrowQueue queue = queueRepository.getBorrowQueueByBook(book);
                if(queue != null && queue.getUsersInQueue().contains(user)) {
                    queue.moveQueue();
                }
                else {
                    book.setAvailableCopies(book.getAvailableCopies()+1);
                }
            }
        }
        return true;
    }
}
