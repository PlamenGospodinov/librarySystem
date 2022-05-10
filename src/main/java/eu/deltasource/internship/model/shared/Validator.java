package eu.deltasource.internship.model.shared;

import java.util.List;

/**
 * Special Validator class which can be reused for validation of setters
 */
public class Validator {

    private static Validator INSTANCE;

    public static Validator validator = Validator.getInstance();

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
    public void validateAgeOrNumberOfTotalCopies(int value, String fieldName) {
        if(!fieldName.equals("age")) {
            if (value > 100) {
                throw new IllegalArgumentException("Enter valid " + fieldName + " ! It can't be more than 100!");
            } else if (value <= 0) {
                throw new IllegalArgumentException("Enter valid " + fieldName + " ! It should be at least 1!");
            }
        } else {
            if (value > 130) {
                throw new IllegalArgumentException("Enter valid " + fieldName + " ! It can't be more than 130!");
            } else if (value <= 7) {
                throw new IllegalArgumentException("Enter valid " + fieldName + " ! It should be at least 7!");
            }
        }
    }

    /**
     * Validates objects setters
     *
     * @param object     - Object which has to be validated
     * @param objectName - Name of the object
     */
    public void validateObjectIsNotNull(Object object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException("Enter a valid object of type " + objectName + "! It can't be null!");
        }
    }
}
