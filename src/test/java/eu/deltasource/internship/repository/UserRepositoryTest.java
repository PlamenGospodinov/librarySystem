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
        boolean successfulAdd = userRepoInstance.add(Peshkata);

        // Then
        assertEquals(1, userRepoInstance.getList().size());
        assertTrue(successfulAdd);
    }

    @Test
    void testAddDuplicateUserReturnsFalse() {
        // Given
        Name name = new Name("Ivan", "Minchov", "Vazov");
        Credentials credentials = new Credentials("pesho123", "efs8efs494");
        Address address = new Address("Bulgaria", "Plovdiv", "bul Bulgaria 128");
        User Peshkata = new User(name, credentials, address, 19, Sex.MALE, Role.REGULAR, "peshooo@abv.bg", true);
        boolean successfulAdd = userRepoInstance.add(Peshkata);

        // When
        boolean failedAdd = userRepoInstance.add(Peshkata);

        // Then
        assertEquals(1, userRepoInstance.getList().size());
        assertTrue(successfulAdd);
        assertFalse(failedAdd);
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