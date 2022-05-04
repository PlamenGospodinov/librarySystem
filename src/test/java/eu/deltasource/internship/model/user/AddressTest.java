package eu.deltasource.internship.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsNull() {
        // given

        // when
        Executable countrySetterException = () -> new Address(null, "NY", "Times Square 26");

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsEmpty() {
        // given

        // when
        Executable countrySetterException = () -> new Address("", "NY", "Times Square 26");

        // then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCityIsNull() {
        // given

        // when
        Executable citySetterException = () -> new Address("USA", null, "Times Square 26");

        // then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCityIsEmpty() {
        // given

        // when
        Executable citySetterException = () -> new Address("USA", "", "Times Square 26");

        // then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfStreetIsNull() {
        // given

        // when
        Executable streetSetterException = () -> new Address("USA", "NY", null);

        // then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfStreetIsEmpty() {
        // given

        // when
        Executable streetSetterException = () -> new Address("USA", "NY", "");

        // then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }
}