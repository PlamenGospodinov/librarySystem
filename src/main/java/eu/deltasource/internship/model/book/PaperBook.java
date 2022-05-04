package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.shared.Validator;

import java.util.List;

/**
 * PaperBook class which extends the base Book class and has total copies in addition
 */
public class PaperBook extends Book {

    private int totalCopies;

    /**
     * Constructor for the PaperBook class
     *
     * @param title - Title of the book
     * @param authors - List of book authors
     * @param genres - list of book genres
     * @param summary - A little summary of the book
     * @param ISBN - International Standard Book Number
     * @param tags - List of book tags
     * @param totalCopies - total copies of this book
     */
    public PaperBook(String title, List<Author> authors, List<Genre> genres, String summary, String ISBN, List<String> tags, int totalCopies) {
        super(title, authors, genres, summary, ISBN, tags);
        setTotalCopies(totalCopies);
    }

    private void setTotalCopies(int totalCopies) {
        Validator.getInstance().validateIntIsNotTooSmallOrTooBig(totalCopies, "total copies");
        this.totalCopies = totalCopies;
    }
}
