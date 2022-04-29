package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.enumeration.Genre;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaperBookTest {

    @Test
    void testSetTotalCopiesToMoreThan100ThrowsAnException() {
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
        Executable totalCopiesSetterException = () -> new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 101);

        // then
        assertThrows(IllegalArgumentException.class, totalCopiesSetterException);
    }

    @Test
    void testSetTotalCopiesToOrLessThan0ThrowsAnException() {
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
        PaperBook harryPotter = new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, 99  );

        // when
        Executable totalCopiesSetterException = () -> new PaperBook("Harry Potter", authors, genres, "Some summary", "15-9-8-45", tags, -2);


        // then
        assertThrows(IllegalArgumentException.class, totalCopiesSetterException);
    }
}