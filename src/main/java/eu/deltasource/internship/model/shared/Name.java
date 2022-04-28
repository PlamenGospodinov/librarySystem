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
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    /**
     * @param value     - Value which has to be validated
     * @param fieldName - Name of the field whose setter we validate
     */
    private void setterValidator(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Enter a valid " + fieldName + " name! It can't be null or empty!");
        }
    }

    public void setFirstName(String firstName) {
        setterValidator(firstName, "first name");
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        setterValidator(secondName, "second name");
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        setterValidator(lastName, "last name");
        this.lastName = lastName;
    }
}
