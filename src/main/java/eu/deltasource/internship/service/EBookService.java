package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Status;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.model.history.CollectiveHistory;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.repository.CollectiveHistoryRepository;
import eu.deltasource.internship.repository.EBookRepository;

import java.util.List;
import java.util.Set;

public class EBookService {

    private static EBookService INSTANCE;

    private final EBookRepository repository = EBookRepository.getInstance();

    private final CollectiveHistoryRepository historyRepository = CollectiveHistoryRepository.getInstance();

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

    public String read(EBook eBook, User user) {
        if(eBook.getLinkForReading() == null) {
            throw new IllegalArgumentException("No read link for this book!");
        }
        if(historyRepository.getCollectiveHistoryByUser(user) == null) {
            historyRepository.addCollectiveHistory(new CollectiveHistory(user));
        }
        historyRepository.getCollectiveHistoryByUser(user).addToEBookRecords(eBook, Status.READ);
        return repository.read(eBook);
    }

    public String download(EBook eBook, User user) {
        if(eBook.getLinkForDownloading() == null || eBook.getLinkForDownloading().isBlank()) {
            throw new IllegalArgumentException("No download link for this book!");
        }
        if(historyRepository.getCollectiveHistoryByUser(user) == null) {
            historyRepository.addCollectiveHistory(new CollectiveHistory(user));
        }
        historyRepository.getCollectiveHistoryByUser(user).addToEBookRecords(eBook, Status.DOWNLOADED);
        return repository.download(eBook);
    }

    public boolean deleteByIsbn(String isbn) {
        return repository.removeByIsbn(isbn);
    }

    public Set<EBook> searchByAuthorNames(String name) {
        return repository.searchByAuthorsName(name);
    }

    public Set<EBook> searchByTitle(String title) {
        return repository.searchByTitle(title);
    }

    public EBook searchByIsbn(String isbn) {
        return repository.searchByIsbn(isbn);
    }

    public Set<EBook> searchByTag(String tag) {
        return repository.searchByTag(tag);
    }

    public Set<EBook> searchByGenre(String genre) {
        return repository.searchByGenre(genre);
    }

    public Set<EBook> getList() {
        return repository.getList();
    }
}
