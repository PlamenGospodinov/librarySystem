package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;

import java.util.List;
import java.util.Objects;

/**
 * Extends the base Book class and has total copies in addition
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
     * @param isbn - International Standard Book Number
     * @param tags - List of book tags
     * @param totalCopies - total copies of this book
     */
    public PaperBook(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, int totalCopies) {
        super(title, authors, genres, summary, isbn, tags);
        setTotalCopies(totalCopies);
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    private void setTotalCopies(int totalCopies) {
        if (totalCopies > 100) {
            throw new IllegalArgumentException("Enter valid " + totalCopies + " ! It can't be more than 100!");
        } else if (totalCopies <= 0) {
            throw new IllegalArgumentException("Enter valid " + totalCopies + " ! It should be at least 1!");
        }
        this.totalCopies = totalCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaperBook paperBook = (PaperBook) o;
        return totalCopies == paperBook.totalCopies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalCopies);
    }
}
