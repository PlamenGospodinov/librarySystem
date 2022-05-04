package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorThrowsAnExceptionIfNamesAreNull() {
        // given
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable namesSetterException = () -> new User(null,credentials,address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, namesSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfCredentialsAreNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable credentialsSetterException = () -> new User(name,null, address,18, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, credentialsSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAgeIsLessThan7() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable ageSetterException = () -> new User(name, credentials, address,6, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, ageSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfAgeIsMoreThan130() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable ageSetterException = () -> new User(name, credentials, address,131, Sex.MALE, Role.REGULAR,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, ageSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfSexIsNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable sexSetterException = () -> new User(name, credentials, address,13, null, Role.REGULAR,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, sexSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfRoleIsNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable roleSetterException = () -> new User(name, credentials, address,13, Sex.MALE, null,"blabla@abv.bg",true);

        // then
        assertThrows(IllegalArgumentException.class, roleSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEmailIsNull() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable emailSetterException = () -> new User(name, credentials, address,13, Sex.MALE, Role.REGULAR,null,true);

        // then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEmailIsBlank() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable emailSetterException = () -> new User(name, credentials, address,13, Sex.MALE, Role.REGULAR,"",true);

        // then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }

    @Test
    void testConstructorThrowsAnExceptionIfEUGDPRIsFalse() {
        // given
        Name name = new Name("Ivan", "Ivanov", "Ivanov");
        Credentials credentials = new Credentials("Ivancho","erws5s4fwf56sd");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // when
        Executable emailSetterException = () -> new User(name, credentials, address,11, Sex.MALE, Role.REGULAR,"null",false);

        // then
        assertThrows(IllegalArgumentException.class, emailSetterException);
    }
}