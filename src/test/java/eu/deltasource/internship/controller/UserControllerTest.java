package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserRepository repository = UserRepository.getInstance();
    UserController controller = new UserController();
    @AfterEach
    void clearRepository() {
        repository.clearRepository();
    }

    @Test
    void testRegisterUserSuccessfully() {
        // Given
        Credentials creds = new Credentials("keke","keke");
        Name name = new Name("Ivan", "Ivanov", "Nikolov");
        Address address = new Address("Bulgaria", "Sofia", "Bulgaria 123");
        User testUser = new User(name, creds,address, 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertEquals(1, repository.getList().size());
        assertEquals(repository.get(creds), testUser);
    }

    @Test
    void testRegisterUserWithSameUsernameReturnsNull() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User newRegistered = controller.register("Ivan", "Ivanov", "Peshev", "keke", "12345", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertEquals(1, repository.getList().size());
        assertNull(newRegistered);
    }

    @Test
    void testLoginWithValidCredentialsIsSuccessful() {
        // Given
        Credentials creds = new Credentials("keke","keke");
        Name name = new Name("Ivan", "Ivanov", "Nikolov");
        Address address = new Address("Bulgaria", "Sofia", "Bulgaria 123");
        User testUser = new User(name, creds,address, 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User loggedUser = controller.login("keke", "keke");

        // Then
        assertEquals(testUser, loggedUser);
    }

    @Test
    void testLoginWithInvalidCredentialsIsReturnsNull() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User loggedUser = controller.login("ke", "keke");

        // Then
        assertNull(loggedUser);
    }

    @Test
    void testRemoveExistingUserSuccessfully() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = controller.remove("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertTrue(successfulRemove);
        assertEquals(0, repository.getList().size());
    }

    @Test
    void testRemoveNonExistantUserReturnsFalse() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = controller.remove("Iv", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertFalse(successfulRemove);
        assertEquals(1, repository.getList().size());
    }

    @Test
    void testRemoveUserByUsernameSuccessfully() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = controller.removeByUsername("keke");

        // Then
        assertTrue(successfulRemove);
        assertEquals(0, repository.getList().size());
    }

    @Test
    void testRemoveUserByInvalidUsernameReturnsFalse() {
        // Given
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = controller.removeByUsername("Pehsjo");

        // Then
        assertFalse(successfulRemove);
        assertEquals(1, repository.getList().size());
    }

    @Test
    void testReturnAUserListSuccessfully() {
        // Given
        Credentials creds = new Credentials("keke","keke");
        Name name = new Name("Ivan", "Ivanov", "Nikolov");
        Address address = new Address("Bulgaria", "Sofia", "Bulgaria 123");
        User testUser = new User(name, creds,address, 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);
        controller.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        Set<User> userList = controller.getList();

        // Then
        assertEquals(1, userList.size());
        assertTrue(userList.contains(testUser));
    }
}