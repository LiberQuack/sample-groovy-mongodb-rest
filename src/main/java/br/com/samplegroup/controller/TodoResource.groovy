package br.com.samplegroup.controller

import static spark.Spark.*

class TodoResource implements Resource{

    private final String CONTEXT = "/api/v1/todos"

    @Override
    def setup() {
        get("${CONTEXT}/todos", "application/json", {req, res ->

        })

        get("${CONTEXT}/todos/:id", "application/json", {req, res ->

        })

        post("${CONTEXT}/todos", "application/json", {req, res ->

        })

        put("${CONTEXT}/todos/:id", "application/json", {req, res ->

        })

        delete("${CONTEXT}/todos/:id", "application/json", {req, res ->

        })

    }
}
