package br.com.samplegroup.interfaces

import spark.ResponseTransformer

interface Transformer extends ResponseTransformer{

    Object unrender(Object message, Class classOf)

}
