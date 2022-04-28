package eu.deltasource.internship.model.user;

/**
 * Address class which holds all the information about one's address
 */
public class Address {

    private String country;

    private String city;

    private String street;

    /**
     * Constructor for the Address class
     * @param country
     * @param city
     * @param street
     */
    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    /**
     * @param value     - Value which has to be validated
     * @param fieldName - Name of the field whose setter we validate
     */
    private void setterValidator(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Enter a valid " + fieldName + " name! It can't be null or empty!");
        }
    }

    public void setCountry(String country) {
        setterValidator(country, "country");
        this.country = country;
    }

    public void setCity(String city) {
        setterValidator(city, "city");
        this.city = city;
    }

    public void setStreet(String street) {
        setterValidator(street, "street");
        this.street = street;
    }
}
