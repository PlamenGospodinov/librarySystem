package eu.deltasource.internship.model.book;

import eu.deltasource.internship.exception.SetterValidationException;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.model.shared.Validator;

import java.time.LocalDate;
import java.util.Objects;

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

    Validator validator = Validator.getInstance();

    private void setName(Name name) {
        if(name == null) {
            throw new SetterValidationException("name");
        }
        this.name = name;
    }

    private void setCountry(String country) {
        validator.validateNotBlank(country);
        this.country = country;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth can't be null!");
        }
        if(dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth can't be in the future!");
        }
        this.dateOfBirth = dateOfBirth;
    }

    private void setDateOfDeath(LocalDate dateOfDeath) {
        if(dateOfDeath.isBefore(dateOfBirth)) {
            throw new IllegalArgumentException("Date of death can't be before date of birth!");
        }
        this.dateOfDeath = dateOfDeath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) && country.equals(author.country) && dateOfBirth.equals(author.dateOfBirth) && Objects.equals(dateOfDeath, author.dateOfDeath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, dateOfBirth, dateOfDeath);
    }
}
