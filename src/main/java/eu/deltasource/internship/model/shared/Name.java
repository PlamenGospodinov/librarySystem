package eu.deltasource.internship.model.shared;

import java.util.Objects;

import static eu.deltasource.internship.model.shared.Validator.validator;

/**
 * Used from both Author and User to store all names
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
        validator.validateStringIsNotEmptyOrNull(firstName, "first name");
        this.firstName = firstName;
    }

    private void setSecondName(String secondName) {
        validator.validateStringIsNotEmptyOrNull(secondName, "second name");
        this.secondName = secondName;
    }

    private void setLastName(String lastName) {
        validator.validateStringIsNotEmptyOrNull(lastName, "last name");
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
