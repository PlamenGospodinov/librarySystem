package eu.deltasource.internship.model.user;

import eu.deltasource.internship.model.shared.Validator;

/**
 * Address class which holds all the information about one's address
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

    public void setCountry(String country) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(country, "country");
        this.country = country;
    }

    public void setCity(String city) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(city, "city");
        this.city = city;
    }

    public void setStreet(String street) {
        Validator.getInstance().validateStringIsNotEmptyOrNull(street, "street");
        this.street = street;
    }
}
