package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A repository for the users
 */
public final class UserRepository {

    private final List<User> userList = new ArrayList<>();

    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public void addBook(User user) {
        userList.add(user);
    }

    public void removeBook(User user) {
        userList.remove(user);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(userList);
    }
}
