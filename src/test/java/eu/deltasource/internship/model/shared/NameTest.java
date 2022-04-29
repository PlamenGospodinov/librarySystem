package eu.deltasource.internship.model.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testSetFirstNameToNullThrowsAnException() {
        // given

        // when
        Executable firstNameSetterException = () -> new Name(null, "Ivanov", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testSetFirstNameToEmptyThrowsAnException() {
        // given

        // when
        Executable firstNameSetterException = () -> new Name("", "Ivanov", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testSetSecondNameToNullThrowsAnException() {
        // given

        // when
        Executable secondNameSetterException = () -> new Name("Georgi", null, "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testSetSecondNameToEmptyThrowsAnException() {
        // given
        Name gosho = new Name("Georgi", "Ivanov", "Ivanov");

        // when
        Executable secondNameSetterException = () -> new Name("Georgi", "", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testSetLastNameToNullThrowsAnException() {
        // given

        // when
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", null);

        // then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }

    @Test
    void testSetLastNameToEmptyThrowsAnException() {
        // given

        // when
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", "");

        // then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }
}