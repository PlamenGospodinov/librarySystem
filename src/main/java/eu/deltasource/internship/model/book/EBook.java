package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.shared.Validator;

import java.util.List;
import java.util.Objects;

/**
 * Extends the base Book class and has link for reading and downloading in addition
 */
public class EBook extends Book{

    private String linkForReading;

    private String linkForDownloading;

    /**
     * Constructor for the EBook class
     *
     * @param title - Title of the book
     * @param authors - List of book authors
     * @param genres - list of book genres
     * @param summary - A little summary of the book
     * @param isbn - International Standard Book Number
     * @param tags - List of book tags
     * @param linkForReading - Link for reading
     * @param linkForDownloading - Link for downloading
     */
    public EBook(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, String linkForReading, String linkForDownloading) {
        super(title, authors, genres, summary, isbn, tags);
        setLinkForReading(linkForReading);
        setLinkForDownloading(linkForDownloading);
    }

    Validator validator = Validator.getInstance();

    private void setLinkForReading(String linkForReading) {
        validator.validateNotBlank(linkForReading);
        this.linkForReading = linkForReading;
    }

    private void setLinkForDownloading(String linkForDownloading) {
        this.linkForDownloading = linkForDownloading;
    }

    public String getLinkForReading() {
        return linkForReading;
    }

    public String getLinkForDownloading() {
        return linkForDownloading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EBook eBook = (EBook) o;
        return linkForReading.equals(eBook.linkForReading) && Objects.equals(linkForDownloading, eBook.linkForDownloading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), linkForReading, linkForDownloading);
    }
}
