package eu.deltasource.internship.repository;

import eu.deltasource.internship.model.user.ActiveUser;
import eu.deltasource.internship.model.user.Credentials;
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

    public User add(User user) {
        for(User userInList : userList) {
            if(userInList.getCredentials().getUsername().equals(user.getCredentials().getUsername())) {
                return null;
            }
        }

        if (!userList.contains(user)) {
            userList.add(user);
            return user;
        }
        return null;
    }

    public User get(Credentials credentials) {
        for(User user : userList) {
            if(user.getCredentials().equals(credentials)) {
                ActiveUser.getInstance().setActiveUser(user);
                return user;
            }
        }
        return null;
    }

    public void logout() {
        ActiveUser.getInstance().setActiveUser(null);
    }

    public boolean remove(User user) {
        return userList.remove(user);
    }

    public boolean removeByUsername(String username) {
        for(User user : userList) {
            if(user.getCredentials().getUsername().equals(username)) {
                return userList.remove(user);
            }
        }
        return false;
    }

    public void clearRepository() {
        userList.clear();
    }

    public Set<User> getList() {
        return Collections.unmodifiableSet(userList);
    }
}
