package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource
import br.com.samplegroup.dao.TodoDao
import br.com.samplegroup.transformer.JsonT

class Bootstrap {

    public static void main(String[] args) {
        new TodoResource(new TodoDao(), new JsonT())
    }

}
