package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepoInstance = UserRepository.getInstance();

    @AfterEach
    void clearAuthorRepo() {
        userRepoInstance.clearRepository();
    }

    @Test
    void testAddUserSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);

        // When
        userRepoInstance.add(Peshkata);

        // Then
        assertEquals(1, userRepoInstance.getList().size());
    }

    @Test
    void testAddUserWithSameUsernameAsSomeoneElseReturnsNull() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);
        User Peshkata2 = new User(name, credentials, address, 25, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);

        // When
        User failedAdd = userRepoInstance.add(Peshkata2);

        // Then
        assertEquals(1, userRepoInstance.getList().size());
        assertNull(failedAdd);
    }

    @Test
    void testAddDuplicateUserReturnsNull() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);

        // When
        User failedAdd = userRepoInstance.add(Peshkata);

        // Then
        assertEquals(1, userRepoInstance.getList().size());
        assertNull(failedAdd);
    }

    @Test
    void testRemoveUserSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);

        // When
        userRepoInstance.remove(Peshkata);

        // Then
        assertEquals(0, userRepoInstance.getList().size());
    }

    @Test
    void testRemoveUserThatDoesntExistReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);

        // When
        boolean successfulRemove = userRepoInstance.remove(Peshkata);

        // Then
        assertFalse(successfulRemove);
    }

    @Test
    void testRemoveUserByUsernameSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);

        // When
        userRepoInstance.removeByUsername("pesho123");

        // Then
        assertEquals(0, userRepoInstance.getList().size());
    }

    @Test
    void testRemoveUserThatDoesntExistByUsernameReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");

        // When
        boolean successfulRemove = userRepoInstance.removeByUsername("pesho123");

        // Then
        assertFalse(successfulRemove);
    }

    @Test
    void testGetUsernameByCredentialsSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);
        Credentials creds = new Credentials("pesho123", "efs8efs494");

        // When
        User returnedUser = userRepoInstance.get(creds);

        // Then
        assertEquals(Peshkata, returnedUser);
    }

    @Test
    void testGetUsernameByIncorrectCredentialsReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);
        Credentials creds = new Credentials("pesho23", "efs8efs494");

        // When
        User returnedUser = userRepoInstance.get(creds);

        // Then
        assertNull(returnedUser);
    }

    @Test
    void testGetUserListSuccessfully() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        userRepoInstance.add(Peshkata);

        // When
        Set<User> users = userRepoInstance.getList();

        // Then
        assertTrue(users.contains(Peshkata));
    }
}