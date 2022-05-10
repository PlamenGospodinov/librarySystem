package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testShouldCreateUserSuccessfully() {
        // Given
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        Name names = new Name("Pesho", "Dimitrov", "Stefanov");
        User user0 = new User(names,credentials,address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // When
        User user1 = new User(names,credentials,address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertEquals(user0, user1);
    }

    @Test
    void testConstructorThrowsAnExceptionIfNamesAreNull() {
        // Given
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable namesSetterException = () -> new User(null,credentials,address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, namesSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCredentialsAreNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable credentialsSetterException = () -> new User(name,null, address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, credentialsSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAgeIsLessThan7() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable ageSetterException = () -> new User(name, credentials, address,6, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, ageSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAgeIsMoreThan130() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable ageSetterException = () -> new User(name, credentials, address,131, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, ageSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSexIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable sexSetterException = () -> new User(name, credentials, address,13, null, Role.REGULAR,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, sexSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfRoleIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable roleSetterException = () -> new User(name, credentials, address,13, Sex.MALE, null,"blabla@abv.bg",true);

        // Then
        assertThrows(IllegalArgumentException.class, roleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEmailIsNull() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable emailSetterException = () -> new User(name, credentials, address,13, Sex.MALE, Role.REGULAR,null,true);

        // Then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEmailIsBlank() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable emailSetterException = () -> new User(name, credentials, address,13, Sex.MALE, Role.REGULAR,"",true);

        // Then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEUGDPRIsFalse() {
        // Given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        Executable emailSetterException = () -> new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"null",false);

        // Then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }
}