package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Book;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.SearchBook;

import java.util.*;

/**
 * Stores the ebooks and allows different operations with them
 */
public class EBookRepository implements SearchBook {

    private static final EBookRepository INSTANCE = new EBookRepository();

    private final Set<EBook> eBookList = new HashSet<>();

    private EBookRepository() {
    }

    public static EBookRepository getInstance() {
        return INSTANCE;
    }

    public EBook add(EBook book) {
        if(eBookList.add(book)) {
            return book;
        }
        return null;
    }

    public boolean remove(EBook book) {
        return eBookList.remove(book);
    }

    public void clearRepository() {
        eBookList.clear();
    }

    public Set<EBook> getList() {
        return Collections.unmodifiableSet(eBookList);
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
