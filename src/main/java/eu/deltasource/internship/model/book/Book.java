package eu.deltasource.internship.model.book;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Validator;

import java.util.List;
import java.util.Objects;

public abstract class Book {

    protected String title;

    protected List<Author> authors;

    protected List<Genre> genres;

    protected String summary;

    protected String isbn;

    protected List<Tag> tags;

    /**
     * Constructor for the book class
     *
     * @param title - Title of the book
     * @param authors - List of book authors
     * @param genres - list of book genres
     * @param summary - A little summary of the book
     * @param isbn - International Standard Book Number
     * @param tags - List of book tags
     */
    public Book(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags) {
        setTitle(title);
        setAuthors(authors);
        setGenres(genres);
        setSummary(summary);
        setIsbn(isbn);
        setTags(tags);
    }

    Validator validator = Validator.getInstance();

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    private void setTitle(String title) {
        validator.validateNotBlank(title);
        this.title = title;
    }

    private void setAuthors(List<Author> authors) {
        if(authors == null) {
            throw new SetterValidationException("authors list!");
        }
        this.authors = authors;
    }

    private void setGenres(List<Genre> genres) {
        if(genres == null) {
            throw new SetterValidationException("genres list!");
        }
        this.genres = genres;
    }

    private void setSummary(String summary) {
        validator.validateNotBlank(summary);
        this.summary = summary;
    }

    private void setIsbn(String isbn) {
        validator.validateNotBlank(isbn);
        this.isbn = isbn;
    }

    private void setTags(List<Tag> tags) {
        if(tags == null) {
            throw new SetterValidationException("tags list!");
        }
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && authors.equals(book.authors) && genres.equals(book.genres) && summary.equals(book.summary) && isbn.equals(book.isbn) && tags.equals(book.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, genres, summary, isbn, tags);
    }
}
