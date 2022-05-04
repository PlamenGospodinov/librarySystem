package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.shared.Validator;

import java.time.LocalDate;

/**
 * Author class which holds all the needed information about a given author
 */
public class Author {

    private Name name;

    private String country;

    private LocalDate dateOfBirth;

    private LocalDate dateOfDeath;

    public Author(Name name, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        setName(name);
        setCountry(country);
        setDateOfBirth(dateOfBirth);
        setDateOfDeath(dateOfDeath);
    }

    private void setName(Name name) {
        Validator.getInstance().validateObjectIsNotNull(name, "name");
        this.name = name;
    }

    private void setCountry(String country) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(country, "country");
        this.country = country;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth can't be null!");
        }
        this.dateOfBirth = dateOfBirth;
    }

    private void setDateOfDeath(LocalDate dateOfDeath) {
        if(dateOfDeath.isBefore(dateOfBirth)) {
            throw new IllegalArgumentException("Date of death can't be before date of birth!");
        }
        this.dateOfDeath = dateOfDeath;
    }
}
