package eu.deltasource.internship.model.book;

import eu.deltasource.internship.model.shared.Name;

import java.time.LocalDate;

/**
 * Author class which holds all the needed information about a given author
 */
//TODO Complete the Author class
public class Author {

    private Name fullName;

    private String country;

    private LocalDate dateOfBirth;

    private LocalDate dateOfDeath;

    private boolean isAlive;
}
