package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;

import java.util.*;

/**
 * Stores the authors and allows different operations with them
 */
public final class AuthorRepository {

    private static final AuthorRepository INSTANCE = new AuthorRepository();

    private final Set<Author> authorList = new HashSet<>();

    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        return INSTANCE;
    }

    public boolean add(Author author) {
        return authorList.add(author);
    }

    public boolean remove(Author author) {
        return authorList.remove(author);
    }

    public void clearRepository() {
        authorList.clear();
    }

    public Set<Author> getList() {
        return Collections.unmodifiableSet(authorList);
    }
}
