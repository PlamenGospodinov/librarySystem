package eu.deltasource.internship.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    void testShouldCreateAddressSuccessfully() {
        // Given
        Address address0 = new Address("Bulgaria", "NY", "Times Square 26");

        // When
        Address address1 = new Address("Bulgaria", "NY", "Times Square 26");

        // Then
        assertEquals(address0, address1);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsNull() {
        // Given

        // When
        Executable countrySetterException = () -> new Address(null, "NY", "Times Square 26");

        // Then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCountryIsEmpty() {
        // Given

        // When
        Executable countrySetterException = () -> new Address("", "NY", "Times Square 26");

        // Then
        assertThrows(IllegalArgumentException.class, countrySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCityIsNull() {
        // Given

        // When
        Executable citySetterException = () -> new Address("USA", null, "Times Square 26");

        // Then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCityIsEmpty() {
        // Given

        // When
        Executable citySetterException = () -> new Address("USA", "", "Times Square 26");

        // Then
        assertThrows(IllegalArgumentException.class, citySetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfStreetIsNull() {
        // Given

        // When
        Executable streetSetterException = () -> new Address("USA", "NY", null);

        // Then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfStreetIsEmpty() {
        // Given

        // When
        Executable streetSetterException = () -> new Address("USA", "NY", "");

        // Then
        assertThrows(IllegalArgumentException.class, streetSetterException);
    }
}