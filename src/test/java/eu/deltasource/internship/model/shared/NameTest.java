package eu.deltasource.internship.model.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testShouldCreateNameSuccessfully() {
        // Given
        Name Ivan = new Name("Ivan", "Ivanov", "Ivanov");

        // When
        Name Ivan1 = new Name("Ivan", "Ivanov", "Ivanov");

        // Then
        assertEquals(Ivan, Ivan1);
    }

    @Test
    void testConstructorThrowsAnExceptionIfFirstNameIsNull() {
        // Given

        // When
        Executable firstNameSetterException = () -> new Name(null, "Ivanov", "Ivanov");

        // Then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfFirstNameIsEmpty() {
        // Given

        // When
        Executable firstNameSetterException = () -> new Name("", "Ivanov", "Ivanov");

        // Then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSecondNameIsNull() {
        // Given

        // When
        Executable secondNameSetterException = () -> new Name("Georgi", null, "Ivanov");

        // Then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSecondNameIsEmpty() {
        // Given

        // When
        Executable secondNameSetterException = () -> new Name("Georgi", "", "Ivanov");

        // Then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLastNameIsNull() {
        // Given

        // When
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", null);

        // Then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLastNameIsEmpty() {
        // Given

        // When
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", "");

        // Then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }
}