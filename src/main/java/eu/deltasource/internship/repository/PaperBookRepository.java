package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.PaperBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A repository for the Paper Books
 */
public class PaperBookRepository {

    private final List<PaperBook> paperBookList = new ArrayList<>();

    private static final PaperBookRepository INSTANCE = new PaperBookRepository();

    private PaperBookRepository() {
    }

    public static PaperBookRepository getInstance() {
        return INSTANCE;
    }

    public boolean addBook(PaperBook book) {
        if(!paperBookList.contains(book)) {
            paperBookList.add(book);
            return true;
        }
        return false;
    }

    public void removeBook(PaperBook book) {
        paperBookList.remove(book);
    }

    public List<PaperBook> getPaperBooks() {
        return Collections.unmodifiableList(paperBookList);
    }
}
