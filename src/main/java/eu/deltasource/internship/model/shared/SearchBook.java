package eu.deltasource.internship.model.shared;

import eu.deltasource.internship.model.book.Book;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;

import java.util.List;

/**
 * Used from PaperBookController and EBookController to search for a book by a given criteria
 */
public interface SearchBook {

    List<Book> searchByAuthorFirstName(String firstName);

    List<Book> searchByAuthorLastName(String lastName);

    List<Book> searchByTitle(String title);

    Book searchByISBN(String isbn);

    List<Book> searchByGenre(Genre genre);

    List<Book> searchByTag(Tag tag);
}
