package br.com.samplegroup.controller

import br.com.samplegroup.dao.TodoDAO
import br.com.samplegroup.exceptions.ValidationException
import br.com.samplegroup.interfaces.ITransformer
import br.com.samplegroup.model.Todo
import br.com.samplegroup.validator.TodoValidator
import com.google.gson.JsonSyntaxException

import java.awt.PageAttributes

import static spark.Spark.*

class TodoResource {

    final String CONTEXT = "/api/v1/todos"
    def todoValidator = new TodoValidator()

    TodoResource(TodoDAO dao, ITransformer trfm) {
        after({ req, res -> res.type("application/json") })

        get("$CONTEXT", "application/json", { req, res ->
            res.status(200)
            dao.findAll()
        }, trfm)

        get("$CONTEXT/:id", "application/json", { req, res ->
            res.status(200)
            dao.findOne(req.params("id"))
        }, trfm)

        post("$CONTEXT", "application/json", { req, res ->
            def todo = trfm.unrender(req.body(), Todo)
            todoValidator.validate(todo)
            dao.save(todo)
        }, trfm)

        put("$CONTEXT/:id", "application/json", { req, res ->

        }, trfm)

        delete("$CONTEXT/:id", "application/json", { req, res ->

        }, trfm)

        exception(JsonSyntaxException, { e, req, res ->
            res.status(400)
            res.body(trfm.render(e.cause.message))
        })

        exception(ValidationException, {e, req, res ->
            res.status(422)
            res.type("application/json")
            res.body(trfm.render(e.fieldsAndReasons))
        })
    }
}