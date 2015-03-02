package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource
import br.com.samplegroup.dao.TodoDAO
import br.com.samplegroup.transformer.JsonTransformer

import static spark.SparkBase.port

def SparkPort = System.getenv("PORT")?.toInteger() ?: 4567
setPort(SparkPort)
new TodoResource(new TodoDAO(), new JsonTransformer())