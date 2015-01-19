package br.com.samplegroup.model

import br.com.samplegroup.interfaces.Validator

class Todo implements Validator {

    String _id
    String title
    String description
    Date createdOn = new Date()
    boolean isActive = true

    @Override
    Map getErrors() {
        def errs = [:]
        if (!title)         errs.title = "Required field, cannot be null or blank"
        if (!description)   errs.description = "Required field, cannot be null or blank"
        errs
    }
}
