package br.com.samplegroup.controller

import br.com.samplegroup.dao.DAO
import br.com.samplegroup.model.Todo
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

import static spark.Spark.*

class TodoResource extends Resource {

    final String CONTEXT = "/api/v1/todos"
    final Gson gson = new Gson()

    TodoResource(DAO dao) {

        after({ req, res -> res.type(APP_JSON) })

        get("${CONTEXT}", APP_JSON, { req, res ->
            res.status(200)
            dao.find()
        })

        get("${CONTEXT}/:id", APP_JSON, { req, res ->
            res.status(200)
            def todo = gson.fromJson(req.body(), Todo.class)
            dao.find(todo)
        })

        post("${CONTEXT}", APP_JSON, { req, res ->
            def todo = gson.fromJson(req.body(), Todo.class)
            if (todo.getErrors()) {
                res.status(400)
                gson.toJson(todo.getErrors())
            } else {
                res.status(201)
                def result = dao.save(todo)
                gson.toJson(result)
            }
        })

        put("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        delete("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        exception(JsonSyntaxException.class, { e, req, res ->
            res.status(400)
            res.body(gson.toJson(e.cause.message))
        })
    }
}