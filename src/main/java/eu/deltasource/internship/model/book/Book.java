package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base Book class which will be used for the creation of paper books and e-books
 */
public abstract class Book {

    protected String title;

    protected List<Author> authors;

    protected List<Genre> genres;

    protected String summary;

    protected String ISBN;

    protected List<Tag> tags;

    /**
     * Constructor for the book class
     *
     * @param title - Title of the book
     * @param authors - List of book authors
     * @param genres - list of book genres
     * @param summary - A little summary of the book
     * @param ISBN - International Standard Book Number
     * @param tags - List of book tags
     */
    public Book(String title, List<Author> authors, List<Genre> genres, String summary, String ISBN, List<Tag> tags) {
        setTitle(title);
        setAuthors(authors);
        setGenres(genres);
        setSummary(summary);
        setISBN(ISBN);
        setTags(tags);
    }

    private void setTitle(String title) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(title, "title");
        this.title = title;
    }

    private void setAuthors(List<Author> authors) {
        Validator.getInstance().validateListIsNotNull(authors, "author");
        this.authors = authors;
    }

    private void setGenres(List<Genre> genres) {
        Validator.getInstance().validateListIsNotNull(genres, "genres");
        this.genres = genres;
    }

    private void setSummary(String summary) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(summary, "summary");
        this.summary = summary;
    }

    private void setISBN(String ISBN) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(ISBN, "ISBN");
        this.ISBN = ISBN;
    }

    private void setTags(List<Tag> tags) {
        Validator.getInstance().validateListIsNotNull(tags, "tags");
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && authors.equals(book.authors) && genres.equals(book.genres) && summary.equals(book.summary) && ISBN.equals(book.ISBN) && tags.equals(book.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, genres, summary, ISBN, tags);
    }
}
