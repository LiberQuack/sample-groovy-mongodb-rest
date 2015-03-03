package br.com.samplegroup.exceptions

class ValidationException extends Exception {

    Map fieldsAndReasons

    /*TODO: Need to implement exceptions as JSON:API*/
    ValidationException(Map fieldsAndReasons, Object invalidObject) {
        super("The object type of ${invalidObject.getClass()} is invalid")
        this.fieldsAndReasons = [errs:fieldsAndReasons]
    }

}
