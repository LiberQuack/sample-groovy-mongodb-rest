package br.com.samplegroup.exceptions

class ValidationException extends Exception {

    Map fieldsAndReasons

    ValidationException(Map fieldsAndReasons, Object invalidObject) {
        super("The object type of $invalidObject.class is invalid")
        this.fieldsAndReasons = fieldsAndReasons
    }

}
