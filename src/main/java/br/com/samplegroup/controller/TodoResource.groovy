package br.com.samplegroup.controller

import br.com.samplegroup.dao.TodoDao
import br.com.samplegroup.model.Todo
import com.google.gson.Gson

import static spark.Spark.*

class TodoResource extends Resource {

    final String CONTEXT = "/api/v1/todos"
    final TodoDao todoDao = new TodoDao()

    @Override
    def setup() {
        get("${CONTEXT}", APP_JSON, { req, res ->
            res.status(201)
            res.type(APP_JSON)
            todoDao.findAll()
        })

        get("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        post("${CONTEXT}", APP_JSON, { req, res ->
            res.status(201)
            res.type(APP_JSON)
            def todo = new Gson().fromJson(req.body(), Todo.class)
            def result = todoDao.save(todo)
            new Gson().toJson(result)
        })

        put("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        delete("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

    }
}
