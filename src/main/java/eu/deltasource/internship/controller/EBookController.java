package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.service.EBookService;

import java.util.List;
import java.util.Set;

public class EBookController {

    EBookService service = EBookService.getInstance();

    public EBook create(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, String linkForReading, String linkForDownloading) {
        return service.create(title, authors, genres, summary, isbn, tags, linkForReading, linkForDownloading);
    }

    public boolean delete(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, String linkForReading, String linkForDownloading) {
        return service.delete(title, authors, genres, summary, isbn, tags, linkForReading, linkForDownloading);
    }

    public String read(EBook book) {
        return service.read(book);
    }

    public String download(EBook book) {
        return service.read(book);
    }

    public boolean deleteByIsbn(String isbn) {
        return service.deleteByIsbn(isbn);
    }

    public Set<EBook> searchByAuthorNames(String name) {
        return service.searchByAuthorNames(name);
    }

    public Set<EBook> searchByTitle(String title) {
        return service.searchByTitle(title);
    }

    public EBook searchByIsbn(String isbn) {
        return service.searchByIsbn(isbn);
    }

    public Set<EBook> searchByTag(String tag) {
        return service.searchByTag(tag);
    }

    public Set<EBook> searchByGenre(String genre) {
        return service.searchByGenre(genre);
    }

    public Set<EBook> getList() {
        return service.getList();
    }
}
