package eu.deltasource.internship.model.shared;

import java.util.Objects;

/**
 * Used from both Author and User to store all names
 */
public class Name {

    private String firstName;

    private String secondName;

    private String lastName;

    /**
     * Constructor for the Name class
     */
    public Name(String firstName, String secondName, String lastName) {
        setFirstName(firstName);
        setSecondName(secondName);
        setLastName(lastName);
    }

    Validator validator = Validator.getInstance();

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setFirstName(String firstName) {
        validator.validateNotBlank(firstName);
        this.firstName = firstName;
    }

    private void setSecondName(String secondName) {
        validator.validateNotBlank(secondName);
        this.secondName = secondName;
    }

    private void setLastName(String lastName) {
        validator.validateNotBlank(lastName);
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) && secondName.equals(name.secondName) && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, lastName);
    }
}
