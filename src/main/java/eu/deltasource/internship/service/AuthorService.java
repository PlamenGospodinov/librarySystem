package eu.deltasource.internship.service;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.model.shared.Name;
import eu.deltasource.internship.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.Set;

public class AuthorService {

    private static AuthorService INSTANCE;

    private final AuthorRepository repository = AuthorRepository.getInstance();

    private AuthorService() {
    }

    public static AuthorService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AuthorService();
        }
        return INSTANCE;
    }

    public Author create(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        Name name = new Name(firstName, secondName, lastName);
        Author author = new Author(name, country, dateOfBirth, dateOfDeath);
        return repository.add(author);
    }

    public boolean delete(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        Name name = new Name(firstName, secondName, lastName);
        Author author = new Author(name, country, dateOfBirth, dateOfDeath);
        return repository.remove(author);
    }

    public Set<Author> getList() {
        return repository.getList();
    }
}
