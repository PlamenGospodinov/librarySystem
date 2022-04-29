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
        Executable countrySetterException = () -> ny.setCountry(null);

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testSetterValidationOfCountryThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable countrySetterException = () -> ny.setCountry("");

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testSetterValidationOfCityThrowsAnExceptionIfItsNull() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable citySetterException = () -> ny.setCity(null);

        // then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testSetterValidationOfCityThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable citySetterException = () -> ny.setCity("");

        // then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testSetterValidationOfStreetThrowsAnExceptionIfItsNull() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable streetSetterException = () -> ny.setStreet(null);

        // then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }

    @Test
    void testSetterValidationOfStreetThrowsAnExceptionIfItsEmpty() {
        // given
        Address ny = new Address("USA", "NY", "Times Square 26");

        // when
        Executable streetSetterException = () -> ny.setStreet("");

        // then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }
}