package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.user.User;

import java.util.*;

/**
 * Stores the users and allows different operations with them
 */
public final class UserRepository {

    private static final UserRepository INSTANCE = new UserRepository();
    private final Set<User> userList = new HashSet<>();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public boolean add(User user) {
        if (!userList.contains(user)) {
            userList.add(user);
            return true;
        }
        return false;
    }

    public void remove(User user) {
        userList.remove(user);
    }

    public void clearRepository() {
        userList.clear();
    }

    public Set<User> getList() {
        return Collections.unmodifiableSet(userList);
    }
}
