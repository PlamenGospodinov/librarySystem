package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorTest {

    @Test
    void testShouldCreateAuthorSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955, 5, 25);
        LocalDate dateOfDeath = LocalDate.of(2015, 7, 2);
        Author Goshko = new Author(name, "Germany", dateOfBirth, dateOfDeath);

        // When
        Author Vanko = new Author(name, "Germany", dateOfBirth, dateOfDeath);

        // Then
        assertEquals(Goshko, Vanko);
    }

    @Test
    void testConstructorThrowsAnExceptionIfNamesAreNull() {
        // Given
        LocalDate dateOfBirth = LocalDate.of(1955, 5, 25);
        LocalDate dateOfDeath = LocalDate.of(2015, 7, 2);

        // When
        Executable namesSetterException = () -> new Author(null, "Germany", dateOfBirth, dateOfDeath);

        // Then
        assertThrows(IllegalArgumentException.class, namesSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955, 5, 25);
        LocalDate dateOfDeath = LocalDate.of(2015, 7, 2);

        // When
        Executable countrySetterException = () -> new Author(name, null, dateOfBirth, dateOfDeath);

        // Then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsBlank() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955, 5, 25);
        LocalDate dateOfDeath = LocalDate.of(2015, 7, 2);

        // When
        Executable countrySetterException = () -> new Author(name, "", dateOfBirth, dateOfDeath);

        // Then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfDateOfBirthIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfDeath = LocalDate.of(2015, 7, 2);

        // When
        Executable dateOfBirthSetterException = () -> new Author(name, "Germany", null, dateOfDeath);

        // Then
        assertThrows(IllegalArgumentException.class, dateOfBirthSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfDateOfDeathIsBeforeDateOfBirth() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        LocalDate dateOfBirth = LocalDate.of(1955, 5, 25);
        LocalDate dateOfDeath = LocalDate.of(1915, 7, 2);

        // When
        Executable dateOfDeathSetterException = () -> new Author(name, "Germany", dateOfBirth, dateOfDeath);

        // Then
        assertThrows(IllegalArgumentException.class, dateOfDeathSetterException);
    }
}