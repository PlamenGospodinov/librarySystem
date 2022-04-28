package eu.deltasource.internship.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    void testSetterValidationOfCountryThrowsAnExceptionIfItsNull() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable countrySetterExeption = () -> ny.setCountry(null);

        // then
        assertThrows(IllegalArgumentException.class, countrySetterExeption);
    }

    @Test
    void testSetterValidationOfCountryThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable countrySetterExeption = () -> ny.setCountry("");

        // then
        assertThrows(IllegalArgumentException.class, countrySetterExeption);
    }

    @Test
    void testSetterValidationOfCityThrowsAnExceptionIfItsNull() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable citySetterExeption = () -> ny.setCity(null);

        // then
        assertThrows(IllegalArgumentException.class, citySetterExeption);
    }

    @Test
    void testSetterValidationOfCityThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable citySetterExeption = () -> ny.setCity("");

        // then
        assertThrows(IllegalArgumentException.class, citySetterExeption);
    }

    @Test
    void testSetterValidationOfStreetThrowsAnExceptionIfItsNull() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable streetSetterExeption = () -> ny.setStreet(null);

        // then
        assertThrows(IllegalArgumentException.class, streetSetterExeption);
    }

    @Test
    void testSetterValidationOfStreetThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable streetSetterExeption = () -> ny.setStreet("");

        // then
        assertThrows(IllegalArgumentException.class, streetSetterExeption);
    }
}