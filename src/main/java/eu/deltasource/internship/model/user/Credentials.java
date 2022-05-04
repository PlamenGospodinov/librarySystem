package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.shared.Validator;

public class Credentials {

    private String username;

    private String password;

    public Credentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    private void setUsername(String username) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(username, "username");
        this.username = username;
    }

    private void setPassword(String password) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(password, "password");
        this.password = password;
    }
}
