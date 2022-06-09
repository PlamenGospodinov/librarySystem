package eu.deltasource.internship.controller;

import eu.deltasource.internship.model.book.Author;
import eu.deltasource.internship.service.AuthorService;

import java.time.LocalDate;
import java.util.Set;

public class AuthorController {

    AuthorService service = AuthorService.getInstance();

    public Author create(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
       return service.create(firstName, secondName, lastName, country, dateOfBirth, dateOfDeath);
    }
    public boolean delete(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        return service.delete(firstName, secondName, lastName, country, dateOfBirth, dateOfDeath);
    }

    public Set<Author> getList() {
        return service.getList();
    }

}
