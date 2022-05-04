package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.EBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A repository for the EBooks
 */
public class EBookRepository {

    private final List<EBook> eBookList = new ArrayList<>();

    private static final EBookRepository INSTANCE = new EBookRepository();

    private EBookRepository() {
    }

    public static EBookRepository getInstance() {
        return INSTANCE;
    }

    public void addBook(EBook book) {
        eBookList.add(book);
    }

    public void removeBook(EBook book) {
        eBookList.remove(book);
    }

    public List<EBook> getEBooks() {
        return Collections.unmodifiableList(eBookList);
    }
}
