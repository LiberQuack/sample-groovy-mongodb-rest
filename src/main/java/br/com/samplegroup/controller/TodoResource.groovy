package br.com.samplegroup.controller

import br.com.samplegroup.dao.TodoDao
import br.com.samplegroup.model.Todo
import com.google.gson.Gson

import static spark.Spark.*

class TodoResource extends Resource {

    final String CONTEXT = "/api/v1/todos"
    final TodoDao todoDao = new TodoDao()
    final Gson gson = new Gson()

    @Override
    def setup() {
        get("${CONTEXT}", APP_JSON, { req, res ->
            res.status(201)
            res.type(APP_JSON)
            todoDao.find()
        })

        get("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        post("${CONTEXT}", APP_JSON, { req, res ->
            res.type(APP_JSON)
            def todo = gson.fromJson(req.body(), Todo.class)

            if (todo.getErrors()) {
                res.status(400)
                gson.toJson(todo.getErrors())
            } else {
                res.status(201)
                def result = todoDao.save(todo)
                new Gson().toJson(result)
            }
        })

        put("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        delete("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

    }
}
