package br.com.samplegroup.controller

import br.com.samplegroup.dao.DAO


abstract class Resource {

    String APP_JSON = "application/json"

    abstract void setup(DAO dao)

}
