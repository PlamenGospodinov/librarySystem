package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.AuthorRepository;

import java.time.LocalDate;

public class AuthorService {

    private static final AuthorService INSTANCE = new AuthorService();

    AuthorRepository repository = AuthorRepository.getInstance();

    private AuthorService() {
    }

    public static AuthorService getInstance() {
        return INSTANCE;
    }

    public String create(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        if (null == firstName || firstName.isBlank()) {
            return "Enter first name!";
        }

        if (null == secondName || secondName.isBlank()) {
            return "Enter second name!";
        }

        if (null == lastName || lastName.isBlank()) {
            return "Enter last name!";
        }

        if (null == country || country.isBlank()) {
            return "Enter country!";
        }

        if(dateOfBirth == null || dateOfBirth.isAfter(dateOfDeath) ) {
            return "Date of birth can't be after date of death or null!";
        }
        Name name = new Name(firstName, secondName, lastName);
        Author author = new Author(name, country, dateOfBirth, dateOfDeath);
        repository.add(author);
        return "Successfully added an author!";
    }
}
