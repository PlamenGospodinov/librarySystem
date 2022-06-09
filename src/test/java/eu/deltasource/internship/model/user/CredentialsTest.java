package eu.deltasource.internship.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void testShouldCreateUsernameSuccessfully() {
        // Given
        Credentials credentials0 = new Credentials("Misho", "wjdiawd454wfafa");

        // When
        Credentials credentials1 = new Credentials("Misho", "wjdiawd454wfafa");

        // Then
        assertEquals(credentials0, credentials1);
    }

    @Test
    void testConstructorThrowsAnExceptionIfUsernameIsNull() {
        // Given

        // When
        Executable usernameSetterException = () -> new Credentials(null, "wjdiawd454wfafa");

        // Then
        assertThrows(IllegalArgumentException.class, usernameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfUsernameIsEmpty() {
        // Given

        // When
        Executable usernameSetterException = () -> new Credentials("", "wjdiawd454wfafa");

        // Then
        assertThrows(IllegalArgumentException.class, usernameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfPasswordIsNull() {
        // Given

        // When
        Executable passwordSetterException = () -> new Credentials("Wolverine", null);

        // Then
        assertThrows(IllegalArgumentException.class, passwordSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfPasswordIsEmpty() {
        // Given

        // When
        Executable passwordSetterException = () -> new Credentials("Wolverine", "");

        // Then
        assertThrows(IllegalArgumentException.class, passwordSetterException);
    }
}