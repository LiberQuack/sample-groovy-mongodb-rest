package br.com.samplegroup.model

import br.com.samplegroup.interfaces.IValidator

class Todo {

    String _id
    String title
    String description
    Date createdOn = new Date()
    boolean isActive = true

}
