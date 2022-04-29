package eu.deltasource.internship.model.shared;

import java.util.List;

/**
 * Special Validator class which can be reused for validation of setters
 */
public class Validator {

    private static final Validator INSTANCE = new Validator();

    private Validator() {

    }

    public static Validator getInstance() {
        return INSTANCE;
    }

    /**
     * Validates string setters are not null or empty
     *
     * @param value     - Value which has to be validated
     * @param fieldName - Name of the field whose setter we validate
     */
    public void validateStringIsNotEmptyOrNull(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Enter a valid " + fieldName + " name! It can't be null or empty!");
        }
    }

    /**
     * Validates List setters
     *
     * @param value    - Value which has to be validated
     * @param listName - Name of the field whose setter we validate
     */
    public void validateListIsNotNull(List value, String listName) {
        if (value == null) {
            throw new IllegalArgumentException("Enter a valid " + listName + " ! It can't be null!");
        }
    }

    /**
     * Validates int setters - in this case used for total book copies
     *
     * @param value     - Value which has to be validated
     * @param fieldName - Name of the field whose setter we validate
     */
    public void validateIntIsNotNegativeOrTooBig(int value, String fieldName) {
        if (value > 100) {
            throw new IllegalArgumentException("Enter valid " + fieldName + " ! It can't be more than 100!");
        } else if (value <= 0) {
            throw new IllegalArgumentException("Enter valid " + fieldName + " ! It should be at least 1!");
        }
    }
}
