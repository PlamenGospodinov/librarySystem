package eu.deltasource.internship.model.shared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    void testConstructorThrowsAnExceptionIfFirstNameIsNull() {
        // given

        // when
        Executable firstNameSetterException = () -> new Name(null, "Ivanov", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfFirstNameIsEmpty() {
        // given

        // when
        Executable firstNameSetterException = () -> new Name("", "Ivanov", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, firstNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSecondNameIsNull() {
        // given

        // when
        Executable secondNameSetterException = () -> new Name("Georgi", null, "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSecondNameIsEmpty() {
        // given

        // when
        Executable secondNameSetterException = () -> new Name("Georgi", "", "Ivanov");

        // then
        assertThrows(IllegalArgumentException.class, secondNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLastNameIsNull() {
        // given

        // when
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", null);

        // then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfLastNameIsEmpty() {
        // given

        // when
        Executable lastNameSetterException = () -> new Name("Georgi", "Ivanov", "");

        // then
        assertThrows(IllegalArgumentException.class, lastNameSetterException);
    }
}