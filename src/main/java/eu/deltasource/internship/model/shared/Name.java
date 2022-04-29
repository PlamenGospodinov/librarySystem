package eu.deltasource.internship.model.shared;

/**
 * Name class which can be used from both Author and User to store all names
 */
public class Name {

    private String firstName;

    private String secondName;

    private String lastName;

    /**
     * Constructor for the Name class
     *
     * @param firstName
     * @param secondName
     * @param lastName
     */
    public Name(String firstName, String secondName, String lastName) {
        setFirstName(firstName);
        setSecondName(secondName);
        setLastName(lastName);
    }

    private void setFirstName(String firstName) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(firstName, "first name");
        this.firstName = firstName;
    }

    private void setSecondName(String secondName) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(secondName, "second name");
        this.secondName = secondName;
    }

    private void setLastName(String lastName) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(lastName, "last name");
        this.lastName = lastName;
    }
}
