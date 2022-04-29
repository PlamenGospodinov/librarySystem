package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.shared.Validator;

import java.time.LocalDate;

/**
 * Author class which holds all the needed information about a given author
 */
//TODO Make Validation about dateOfBirth and dateOfDeath
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
        this.name = name;
    }

    private void setCountry(String country) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(country, "country");
        this.country = country;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    private void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
