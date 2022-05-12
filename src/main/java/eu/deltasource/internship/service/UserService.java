package eu.deltasource.internship.service;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.user.Address;
import eu.deltasource.internship.model.user.Credentials;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.repository.UserRepository;

import java.util.Set;

public class UserService {

    private static UserService INSTANCE;

    private final UserRepository repository = UserRepository.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public User register(String firstName, String secondName, String lastName, String username, String password, String country, String city, String street, int age, Sex sex, Role role, String email, boolean eugdpr) {
        Name name = new Name(firstName, secondName, lastName);
        Credentials credentials = new Credentials(username, password);
        Address address = new Address(country, city, street);
        User user = new User(name, credentials, address, age, sex, role, email, eugdpr);
        return repository.add(user);
    }

    public User login(String username, String password) {
        Credentials credentials = new Credentials(username, password);
        return repository.get(credentials);
    }

    public boolean delete(String firstName, String secondName, String lastName, String username, String password, String country, String city, String street, int age, Sex sex, Role role, String email, boolean eugdpr) {
        Name name = new Name(firstName, secondName, lastName);
        Credentials credentials = new Credentials(username, password);
        Address address = new Address(country, city, street);
        User user = new User(name, credentials, address, age, sex, role, email, eugdpr);
        return repository.remove(user);
    }

    public boolean deleteByUsername(String username) {
        return repository.removeByUsername(username);
    }

    public Set<User> getList() {
        return repository.getList();
    }
}
