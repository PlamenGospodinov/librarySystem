package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;
import eu.deltasource.internship.service.PaperBookService;

import java.util.List;
import java.util.Set;

public class PaperBookController {

    PaperBookService service = PaperBookService.getInstance();

    public PaperBook create(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, int totalCopies) {
        return service.create(title, authors, genres, summary, isbn, tags, totalCopies);
    }

    public boolean delete(String title, List<Author> authors, List<Genre> genres, String summary, String isbn, List<Tag> tags, int totalCopies) {
        return service.delete(title, authors, genres, summary, isbn, tags, totalCopies);
    }

    public boolean deleteByIsbn(String isbn) {
        return service.deleteByIsbn(isbn);
    }

    public Set<PaperBook> getList() {
        return service.getList();
    }
}
