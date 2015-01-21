package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource
import br.com.samplegroup.dao.TodoDao
import com.google.gson.Gson

class Bootstrap {

    public static void main(String[] args) {
        new TodoResource(new TodoDao())
    }

}
