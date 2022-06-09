package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.shared.Validator;

import java.util.Objects;

public class Address {

    private String country;

    private String city;

    private String street;

    /**
     * Constructor for the Address class
     */
    public Address(String country, String city, String street) {
        setCountry(country);
        setCity(city);
        setStreet(street);
    }

    Validator validator = Validator.getInstance();

    private void setCountry(String country) {
        validator.validateNotBlank(country);
        this.country = country;
    }

    private void setCity(String city) {
        validator.validateNotBlank(city);
        this.city = city;
    }

    private void setStreet(String street) {
        validator.validateNotBlank(street);
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
