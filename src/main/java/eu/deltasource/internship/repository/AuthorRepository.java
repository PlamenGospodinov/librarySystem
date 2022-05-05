package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A repository for the authors
 */
public final class AuthorRepository {

    private final List<Author> authorList = new ArrayList<>();

    private static final AuthorRepository INSTANCE = new AuthorRepository();

    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        return INSTANCE;
    }

    public boolean addAuthor(Author author) {
        if(!authorList.contains(author)) {
            authorList.add(author);
            return true;
        }
        return false;
    }

    public void removeAuthor(Author author) {
        authorList.remove(author);
    }

    public List<Author> getAuthorList() {
        return Collections.unmodifiableList(authorList);
    }
}
