package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.enumeration.Role;
import eu.deltasource.internship.model.enumeration.Sex;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.shared.Validator;

import java.util.Objects;

import static eu.deltasource.internship.model.shared.Validator.validator;

/**
 * Stores the data for each of our users
 */
public class User {

    private Name names;

    private Credentials credentials;

    private Address address;

    private int age;

    private Sex sex;

    private Role role;

    private String email;

    private boolean eugdpr;

    /**
     * Constructor for the User class
     *
     * @param names - User's 3 names
     * @param credentials - username and password
     * @param address - country, city and street
     * @param age
     * @param sex
     * @param role
     * @param email
     * @param eugdpr
     */
    public User(Name names, Credentials credentials, Address address, int age, Sex sex, Role role, String email, boolean eugdpr) {
        setNames(names);
        setCredentials(credentials);
        setAddress(address);
        setAge(age);
        setSex(sex);
        setRole(role);
        setEmail(email);
        setEugdpr(eugdpr);
    }

    private void setNames(Name names) {
        validator.validateObjectIsNotNull(names,"names");
        this.names = names;
    }

    private void setCredentials(Credentials credentials) {
        validator.validateObjectIsNotNull(credentials,"credentials");
        this.credentials = credentials;
    }

    private void setAddress(Address address) {
        validator.validateObjectIsNotNull(address,"address");
        this.address = address;
    }

    private void setAge(int age) {
        validator.validateAgeOrNumberOfTotalCopies(age, "age");
        this.age = age;
    }

    private void setSex(Sex sex) {
        validator.validateObjectIsNotNull(sex,"sex");
        this.sex = sex;
    }

    private void setRole(Role role) {
        validator.validateObjectIsNotNull(role,"role");
        this.role = role;
    }

    private void setEmail(String email) {
        validator.validateStringIsNotEmptyOrNull(email, "email");
        this.email = email;
    }

    private void setEugdpr(boolean eugdpr) {
        if(!eugdpr) {
            throw new IllegalArgumentException("You can't have eugdpr set to false!");
        }
        this.eugdpr = eugdpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && eugdpr == user.eugdpr && names.equals(user.names) && credentials.equals(user.credentials) && address.equals(user.address) && sex == user.sex && role == user.role && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names, credentials, address, age, sex, role, email, eugdpr);
    }
}
