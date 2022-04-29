package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.shared.Validator;

import java.util.List;

/**
 * Ebook class which extends the base Book class and has link for reading and downloading in addition
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
     * @param ISBN - International Standard Book Number
     * @param tags - List of book tags
     * @param linkForReading - Link for reading
     * @param linkForDownloading - Link for downloading
     */
    public EBook(String title, List<Author> authors, List<Genre> genres, String summary, String ISBN, List<String> tags, String linkForReading, String linkForDownloading) {
        super(title, authors, genres, summary, ISBN, tags);
        setLinkForReading(linkForReading);
        setLinkForDownloading(linkForDownloading);
    }

    private void setLinkForReading(String linkForReading) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(linkForReading, "link for reading");
        this.linkForReading = linkForReading;
    }

    private void setLinkForDownloading(String linkForDownloading) {
        this.linkForDownloading = linkForDownloading;
    }
}
