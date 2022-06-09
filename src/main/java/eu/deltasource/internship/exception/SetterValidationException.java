package eu.deltasource.internship.exception;

public class SetterValidationException extends IllegalArgumentException {

        public SetterValidationException(String argument) {
            super("Enter valid " + argument + " !");
        }
}
