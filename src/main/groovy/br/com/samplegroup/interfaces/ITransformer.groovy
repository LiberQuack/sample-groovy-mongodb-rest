package br.com.samplegroup.interfaces

import spark.ResponseTransformer

interface ITransformer extends ResponseTransformer{

    Object unrender(Object message, Class classOf)
    String render(Object model) throws Exception
}