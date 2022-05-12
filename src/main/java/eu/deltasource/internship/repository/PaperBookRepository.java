package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.PaperBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores the paper books and allows different operations with them
 */
public class PaperBookRepository {

    private static final PaperBookRepository INSTANCE = new PaperBookRepository();

    private final Set<PaperBook> paperBookList = new HashSet<>();

    private PaperBookRepository() {
    }

    public static PaperBookRepository getInstance() {
        return INSTANCE;
    }

    public PaperBook add(PaperBook book) {
        if (!paperBookList.contains(book)) {
            paperBookList.add(book);
            return book;
        }
        return null;
    }

    public boolean remove(PaperBook book) {
        return paperBookList.remove(book);
    }

    public boolean removeByIsbn(String isbn) {
        return paperBookList.removeIf(b -> b.getIsbn().equals(isbn));
    }

    public void clearRepository() {
        paperBookList.clear();
    }

    public Set<PaperBook> getList() {
        return Collections.unmodifiableSet(paperBookList);
    }

    public Set<PaperBook> searchByAuthorsName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Inappropriate title! Can't be null or blank!");
        }
        Set<PaperBook> searchResult = new HashSet<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (PaperBook book :paperBookList) {
            for (Author author : book.getAuthors()) {
                String fullName = author.getName().getFirstName() +
                        author.getName().getSecondName() +
                        author.getName().getLastName();
                Matcher matcher = pattern.matcher(fullName);
                if(matcher.find()) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }

    public Set<PaperBook> searchByTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Inappropriate title! Can't be null or blank!");
        }
        Set<PaperBook> searchResult = new HashSet<>();
        Pattern pattern = Pattern.compile(title, Pattern.CASE_INSENSITIVE);
        for (PaperBook book :paperBookList) {
            Matcher matcher = pattern.matcher(book.getTitle());
            if(matcher.find()) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public PaperBook searchByIsbn(String isbn) {
        if(isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Inappropriate isbn! Can't be null or blank!");
        }
        for (PaperBook book :paperBookList) {
            if(book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Set<PaperBook> searchByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Inappropriate genre! Can't be null or blank!");
        }
        Set<PaperBook> searchResult = new HashSet<>();
        for (PaperBook book : paperBookList) {
            for (Genre genreSignature : book.getGenres()) {
                if(genreSignature.toString().toLowerCase().equals(genre)) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }

    public Set<PaperBook> searchByTag(String tag) {
        if(tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("Inappropriate tag! Can't be null or blank!");
        }
        Set<PaperBook> searchResult = new HashSet<>();
        for (PaperBook book : paperBookList) {
            for (Tag tagSignature : book.getTags()) {
                if(tagSignature.toString().toLowerCase().equals(tag)) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }
}
