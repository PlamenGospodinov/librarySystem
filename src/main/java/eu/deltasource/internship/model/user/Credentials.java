package eu.deltasource.internship.model.user;

import java.util.Objects;

import static eu.deltasource.internship.model.shared.Validator.validator;

/**
 * Stores the username and password of the user
 */
public class Credentials {

    private String username;

    private String password;

    public Credentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    private void setUsername(String username) {
        validator.validateStringIsNotEmptyOrNull(username, "username");
        this.username = username;
    }

    private void setPassword(String password) {
        validator.validateStringIsNotEmptyOrNull(password, "password");
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
