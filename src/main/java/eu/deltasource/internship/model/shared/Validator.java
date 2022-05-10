package eu.deltasource.internship.model.shared;

import eu.deltasource.internship.exception.SetterValidationException;

/**
 * Special Validator class which can be reused for validation of setters
 */
public class Validator {

    private static Validator INSTANCE;

    private Validator() {
    }

    public static Validator getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Validator();
        }
        return INSTANCE;
    }

    /**
     * Validates string setters are not null or empty
     *
     * @param value     - Value which has to be validated
     */
    public void validateNotBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new SetterValidationException(value);
        }
    }
}
