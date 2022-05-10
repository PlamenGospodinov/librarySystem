package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Book;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.SearchBook;

import java.util.*;

/**
 * Stores the paper books and allows different operations with them
 */
public class PaperBookRepository implements SearchBook {

    private static final PaperBookRepository INSTANCE = new PaperBookRepository();

    private final Set<PaperBook> paperBookList = new HashSet<>();

    private PaperBookRepository() {
    }

    public static PaperBookRepository getInstance() {
        return INSTANCE;
    }

    public boolean add(PaperBook book) {
        if (!paperBookList.contains(book)) {
            paperBookList.add(book);
            return true;
        }
        return false;
    }

    public void remove(PaperBook book) {
        paperBookList.remove(book);
    }

    public void clearRepository() {
        paperBookList.clear();
    }

    public Set<PaperBook> getList() {
        return Collections.unmodifiableSet(paperBookList);
    }

    @Override
    public List<Book> searchByAuthorFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Book> searchByAuthorLastName(String lastName) {
        return null;
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return null;
    }

    @Override
    public Book searchByISBN(String isbn) {
        return null;
    }

    @Override
    public List<Book> searchByGenre(Genre genre) {
        return null;
    }

    @Override
    public List<Book> searchByTag(Tag tag) {
        return null;
    }
}
