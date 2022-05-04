package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testConstructorThrowsAnExceptionIfTitleIsNull() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");

        // when
        Executable titleSetterException = () -> new EBook(null, authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, titleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfTitleIsEmpty() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");

        // when
        Executable titleSetterException = () -> new EBook("", authors, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);


        // then
        assertThrows(IllegalArgumentException.class, titleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAuthorsListIsNull() {
        // given
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");

        // when
        Executable authorsSetterException = () -> new EBook("Harry Potter", null, genres, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);


        // then
        assertThrows(IllegalArgumentException.class, authorsSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfGenresListIsNull() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        tags.add("popular");
        // when
        Executable genresSetterException = () -> new EBook("Harry Potter", authors, null, "Some summary", "15-9-8-45", tags, "https://somelink.com", null);


        // then
        assertThrows(IllegalArgumentException.class, genresSetterException);
    }

    @Test
    void setSummaryToNullThrowsAnException() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");
        // when
        Executable summarySetterException = () -> new EBook("Harry Potter", authors, genres, null, "15-9-8-45", tags, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, summarySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSummaryIsEmpty() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");
        // when
        Executable summarySetterException = () -> new EBook("Harry Potter", authors, genres, "", "15-9-8-45", tags, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, summarySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfISBNIsEmpty() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");
        // when
        Executable isbnSetterException = () -> new EBook("Harry Potter", authors, genres, "Brief summary", "", tags, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, isbnSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfISBNIsNull() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");
        // when
        Executable isbnSetterException = () -> new EBook("Harry Potter", authors, genres, "", null, tags, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, isbnSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfTagsListIsNull() {
        // given
        Name name = new Name("Gosho","Goshev", "Goshev");
        LocalDate date = LocalDate.now();
        Author sb = new Author(name, "Bulgaria", date, date);
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        authors.add(sb);
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.SUSPENSE);
        tags.add("popular");
        // when
        Executable tagsSetterException = () -> new EBook("Harry Potter", authors, genres, "", "25-69-582", null, "https://somelink.com", null);

        // then
        assertThrows(IllegalArgumentException.class, tagsSetterException);
    }
/*

    @Test
    void setTags() {
    }*/
}