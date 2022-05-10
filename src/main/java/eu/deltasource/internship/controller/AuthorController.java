package eu.deltasource.internship.controller;

import eu.deltasource.internship.service.AuthorService;

import java.time.LocalDate;

public class AuthorController {

    AuthorService service = AuthorService.getInstance();

    public String create(String firstName, String secondName, String lastName, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
       return service.create(firstName, secondName, lastName, country, dateOfBirth, dateOfDeath);
    }
}
