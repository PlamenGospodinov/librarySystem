package eu.deltasource.internship.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void testConstructorThrowsAnExceptionIfUsernameIsNull() {
        // given

        // when
        Executable usernameSetterException = () -> new Credentials(null, "wjdiawd454wfafa");

        // then
        assertThrows(IllegalArgumentException.class, usernameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfUsernameIsEmpty() {
        // given

        // when
        Executable usernameSetterException = () -> new Credentials("", "wjdiawd454wfafa");

        // then
        assertThrows(IllegalArgumentException.class, usernameSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfPasswordIsNull() {
        // given

        // when
        Executable passwordSetterException = () -> new Credentials("Wolverine", null);

        // then
        assertThrows(IllegalArgumentException.class, passwordSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfPasswordIsEmpty() {
        // given

        // when
        Executable passwordSetterException = () -> new Credentials("Wolverine", "");

        // then
        assertThrows(IllegalArgumentException.class, passwordSetterException);
    }
}