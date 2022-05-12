package eu.deltasource.internship.model.user;

import eu.deltasource.internship.exception.SetterValidationException;

public class ActiveUser {

    private User activeUser;

    private static ActiveUser INSTANCE;

    private ActiveUser() {
    }

    public static ActiveUser getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActiveUser();
        }
        return INSTANCE;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User user) {
        if(user == null) {
            throw new SetterValidationException("user");
        }
        activeUser = user;
    }
}
