package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.book.EBook;
import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.enumeration.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores the ebooks and allows different operations with them
 */
public class EBookRepository {

    private static final EBookRepository INSTANCE = new EBookRepository();

    private final Set<EBook> eBookList = new HashSet<>();

    private EBookRepository() {
    }

    public static EBookRepository getInstance() {
        return INSTANCE;
    }

    public EBook add(EBook book) {
        if(eBookList.add(book)) {
            return book;
        }
        return null;
    }

    public boolean remove(EBook book) {
        return eBookList.remove(book);
    }

    public boolean removeByIsbn(String isbn) {
        return eBookList.removeIf(b -> b.getIsbn().equals(isbn));
    }

    public void clearRepository() {
        eBookList.clear();
    }

    public Set<EBook> getList() {
        return Collections.unmodifiableSet(eBookList);
    }

    public Set<EBook> searchByAuthorsName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Inappropriate title! Can't be null or blank!");
        }
        Set<EBook> searchResult = new HashSet<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (EBook book :eBookList) {
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

    public Set<EBook> searchByTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Inappropriate title! Can't be null or blank!");
        }
        Set<EBook> searchResult = new HashSet<>();
        Pattern pattern = Pattern.compile(title, Pattern.CASE_INSENSITIVE);
        for (EBook book :eBookList) {
            Matcher matcher = pattern.matcher(book.getTitle());
            if(matcher.find()) {
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public EBook searchByIsbn(String isbn) {
        if(isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Inappropriate isbn! Can't be null or blank!");
        }
        for (EBook book :eBookList) {
            if(book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Set<EBook> searchByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Inappropriate genre! Can't be null or blank!");
        }
        Set<EBook> searchResult = new HashSet<>();
        for (EBook book : eBookList) {
            for (Genre genreSignature : book.getGenres()) {
                if(genreSignature.toString().toLowerCase().equals(genre)) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }

    public Set<EBook> searchByTag(String tag) {
        if(tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("Inappropriate tag! Can't be null or blank!");
        }
        Set<EBook> searchResult = new HashSet<>();
        for (EBook book : eBookList) {
            for (Tag tagSignature : book.getTags()) {
                if(tagSignature.toString().toLowerCase().equals(tag)) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }
}
