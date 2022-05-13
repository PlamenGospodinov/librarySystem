package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.user.User;
import eu.deltasource.internship.service.UserService;

import java.util.Set;

public class UserController {

    UserService service = UserService.getInstance();

    public User register(String firstName, String secondName, String lastName, String username, String password, String country, String city, String street, int age, Sex sex, Role role, String email, boolean eugdpr) {
        return service.register(firstName, secondName, lastName, username, password, country, city, street, age, sex, role, email, eugdpr);
    }

    public User login(String username, String password) {
        return service.login(username, password);
    }

    public void logout() {
        service.logout();
    }

    public boolean remove(String firstName, String secondName, String lastName, String username, String password, String country, String city, String street, int age, Sex sex, Role role, String email, boolean eugdpr) {
        return service.delete(firstName, secondName, lastName, username, password, country, city, street, age, sex, role, email, eugdpr);
    }

    public boolean removeByUsername(String username) {
        return service.deleteByUsername(username);
    }

    public Set<User> getList() {
        return service.getList();
    }
}
