package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.shared.Validator;

import java.util.Objects;

import static eu.deltasource.internship.model.shared.Validator.validator;

/**
 * Holds all the information about one's address
 */
public class Address {

    private String country;

    private String city;

    private String street;

    /**
     * Constructor for the Address class
     *
     * @param country
     * @param city
     * @param street
     */
    public Address(String country, String city, String street) {
        setCountry(country);
        setCity(city);
        setStreet(street);
    }

    private void setCountry(String country) {
        validator.validateStringIsNotEmptyOrNull(country, "country");
        this.country = country;
    }

    private void setCity(String city) {
        validator.validateStringIsNotEmptyOrNull(city, "city");
        this.city = city;
    }

    private void setStreet(String street) {
        validator.validateStringIsNotEmptyOrNull(street, "street");
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return country.equals(address.country) && city.equals(address.city) && street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street);
    }
}
