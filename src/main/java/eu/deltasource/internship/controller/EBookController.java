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

    public Set<EBook> getList() {
        return service.getList();
    }
}
