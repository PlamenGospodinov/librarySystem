package eu.deltasource.internship.service;

import eu.deltasource.internship.controller.UserController;
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

class UserServiceTest {
    UserRepository repository = UserRepository.getInstance();
    UserService service = UserService.getInstance();

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
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertEquals(1, repository.getList().size());
        assertEquals(repository.get(creds), testUser);
    }

    @Test
    void testRegisterUserWithSameUsernameReturnsNull() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User newRegistered = service.register("Ivan", "Ivanov", "Peshev", "keke", "12345", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

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
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User loggedUser = service.login("keke", "keke");

        // Then
        assertEquals(testUser, loggedUser);
    }

    @Test
    void testLoginWithInvalidCredentialsIsReturnsNull() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        User loggedUser = service.login("ke", "keke");

        // Then
        assertNull(loggedUser);
    }

    @Test
    void testRemoveExistingUserSuccessfully() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = service.delete("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertTrue(successfulRemove);
        assertEquals(0, repository.getList().size());
    }

    @Test
    void testRemoveNonExistantUserReturnsFalse() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = service.delete("Iv", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // Then
        assertFalse(successfulRemove);
        assertEquals(1, repository.getList().size());
    }

    @Test
    void testRemoveUserByUsernameSuccessfully() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = service.deleteByUsername("keke");

        // Then
        assertTrue(successfulRemove);
        assertEquals(0, repository.getList().size());
    }

    @Test
    void testRemoveUserByInvalidUsernameReturnsFalse() {
        // Given
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        boolean successfulRemove = service.deleteByUsername("Pehsjo");

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
        service.register("Ivan", "Ivanov", "Nikolov", "keke", "keke", "Bulgaria", "Sofia", "Bulgaria 123", 15, Sex.MALE, Role.REGULAR, "blabla@abv.bg", true);

        // When
        Set<User> userList = service.getList();

        // Then
        assertEquals(1, userList.size());
        assertTrue(userList.contains(testUser));
    }
}