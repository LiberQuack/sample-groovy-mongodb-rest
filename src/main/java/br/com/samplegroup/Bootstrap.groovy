package br.com.samplegroup

import static spark.Spark.get

class Bootstrap {

    static void main(String[] args) {
        get("/", {req, res -> "Hello World"})
    }
}