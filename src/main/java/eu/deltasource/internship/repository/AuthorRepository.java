package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores the authors and allows different operations with them
 */
public final class AuthorRepository {

    private static AuthorRepository INSTANCE;

    private final Set<Author> authorList = new HashSet<>();

    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AuthorRepository();
        }
        return INSTANCE;
    }

    public Author add(Author author) {
        if(authorList.add(author)) {
            return author;
        }
        return null;
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
