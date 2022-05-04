package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorTest {

    @Test
    void testConstructorThrowsAnExceptionIfNamesAreNull() {
        // given
        LocalDate dateOfBirth = LocalDate.of(1955,5,25);
        LocalDate dateOfDeath = LocalDate.of(2015,7,2);

        // when
        Executable namesSetterException = () -> new Author(null, "Germany", dateOfBirth, dateOfDeath);

        // then
        assertThrows(IllegalArgumentException.class, namesSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955,5,25);
        LocalDate dateOfDeath = LocalDate.of(2015,7,2);

        // when
        Executable countrySetterException = () -> new Author(name, null, dateOfBirth, dateOfDeath);

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsBlank() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955,5,25);
        LocalDate dateOfDeath = LocalDate.of(2015,7,2);

        // when
        Executable countrySetterException = () -> new Author(name, "", dateOfBirth, dateOfDeath);

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfDateOfBirthIsNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfDeath = LocalDate.of(2015,7,2);

        // when
        Executable dateOfBirthSetterException = () -> new Author(name, "Germany", null, dateOfDeath);

        // then
        assertThrows(IllegalArgumentException.class, dateOfBirthSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfDateOfDeathIsBeforeDateOfBirth() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955,5,25);
        LocalDate dateOfDeath = LocalDate.of(1915,7,2);

        // when
        Executable dateOfDeathSetterException = () -> new Author(name, "Germany", dateOfBirth, dateOfDeath);

        // then
        assertThrows(IllegalArgumentException.class, dateOfDeathSetterException);
    }
}