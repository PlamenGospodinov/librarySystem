package eu.deltasource.internship.exception;

public class SetterValidationException extends IllegalArgumentException {

    //Some comment
        public SetterValidationException(String argument) {
            super("Enter valid " + argument + " !");
        }
}
