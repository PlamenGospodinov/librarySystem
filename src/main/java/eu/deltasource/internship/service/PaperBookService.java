package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.repository.PaperBookRepository;

import java.util.List;
import java.util.Set;

public class PaperBookService {
    private static PaperBookService INSTANCE;

    private final PaperBookRepository repository = PaperBookRepository.getInstance();

    private PaperBookService() {
    }

    public static PaperBookService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaperBookService();
        }
        return INSTANCE;
    }

    public PaperBook create(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, int totalCopies) {
        PaperBook book = new PaperBook(title, authors, genres, summary, isbn, tags, totalCopies);
        return repository.add(book);
    }

    public boolean delete(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, int totalCopies) {
        PaperBook book = new PaperBook(title, authors, genres, summary, isbn, tags, totalCopies);
        return repository.remove(book);
    }

    public boolean deleteByIsbn(String isbn) {
        return repository.removeByIsbn(isbn);
    }

    public Set<PaperBook> searchByAuthorNames(String name) {
        return repository.searchByAuthorsName(name);
    }

    public Set<PaperBook> searchByTitle(String title) {
        return repository.searchByTitle(title);
    }

    public PaperBook searchByIsbn(String isbn) {
        return repository.searchByIsbn(isbn);
    }

    public Set<PaperBook> searchByTag(String tag) {
        return repository.searchByTag(tag);
    }

    public Set<PaperBook> searchByGenre(String genre) {
        return repository.searchByGenre(genre);
    }

    public Set<PaperBook> getList() {
        return repository.getList();
    }
}
