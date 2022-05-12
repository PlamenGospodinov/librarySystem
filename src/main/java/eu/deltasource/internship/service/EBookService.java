package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.repository.EBookRepository;

import java.util.List;
import java.util.Set;

public class EBookService {
    private static EBookService INSTANCE;

    private final EBookRepository repository = EBookRepository.getInstance();

    private EBookService() {
    }

    public static EBookService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EBookService();
        }
        return INSTANCE;
    }

    public EBook create(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, String linkForReading, String linkForDownloading) {
        EBook ebook = new EBook(title, authors, genres, summary, isbn, tags, linkForReading, linkForDownloading);
        return repository.add(ebook);
    }

    public boolean delete(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, String linkForReading, String linkForDownloading) {
        EBook ebook = new EBook(title, authors, genres, summary, isbn, tags, linkForReading, linkForDownloading);
        return repository.remove(ebook);
    }

    public boolean deleteByIsbn(String isbn) {
        return repository.removeByIsbn(isbn);
    }

    public Set<EBook> getList() {
        return repository.getList();
    }
}
