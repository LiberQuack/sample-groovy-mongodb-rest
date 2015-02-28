package br.com.samplegroup.controller

import br.com.samplegroup.dao.DAO
import br.com.samplegroup.interfaces.Transformer
import br.com.samplegroup.model.Todo
import com.google.gson.JsonSyntaxException

import static spark.Spark.*

class TodoResource {

    final String CONTEXT = "/api/v1/todos"

    TodoResource(DAO dao, Transformer trfm) {
        after({ req, res -> res.type("application/json") })

        get("$CONTEXT", "application/json", { req, res ->
            res.status(200)
            dao.findAll()
        }, trfm)

        get("$CONTEXT/:id", "application/json", { req, res ->
            res.status(200)
            def todo = trfm.unrender(req.body(), Todo.class)
            dao.findOne(todo)
        }, trfm)

        post("$CONTEXT", "application/json", { req, res ->
            def todo = trfm.unrender(req.body(), Todo.class)
            if (todo.getErrors()) {
                res.status(400)
                todo.getErrors()
            } else {
                res.status(201)
                dao.save(todo)
            }
        }, trfm)

        put("$CONTEXT/:id", "application/json", { req, res ->

        }, trfm)

        delete("$CONTEXT/:id", "application/json", { req, res ->

        }, trfm)

        exception(JsonSyntaxException.class, { e, req, res ->
            res.status(400)
            res.body(trfm.render(e.cause.message))
        })
    }
}