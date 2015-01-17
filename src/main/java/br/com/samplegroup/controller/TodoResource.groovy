package br.com.samplegroup.controller

import static spark.Spark.*

class TodoResource extends Resource {

    private final String CONTEXT = "/api/v1/todos"

    @Override
    def setup() {
        get("${CONTEXT}", APP_JSON, { req, res ->

        })

        get("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        post("${CONTEXT}", APP_JSON, { req, res ->

        })

        put("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

        delete("${CONTEXT}/:id", APP_JSON, { req, res ->

        })

    }
}
