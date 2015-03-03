package br.com.samplegroup.validator

import br.com.samplegroup.exceptions.ValidationException
import br.com.samplegroup.interfaces.IValidator
import br.com.samplegroup.model.Todo

class TodoValidator implements IValidator {

    @Override
    void validate(Object todo) throws ValidationException {
        def errs = [:]
        if (!todo.title)         errs.title = "Required field, cannot be null or blank"
        if (!todo.description)   errs.description = "Required field, cannot be null or blank"
        if (errs) {throw new ValidationException(errs, todo)}
    }


}
