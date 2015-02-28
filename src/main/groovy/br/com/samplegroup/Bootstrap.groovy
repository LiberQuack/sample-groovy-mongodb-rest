package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource
import br.com.samplegroup.dao.TodoDAO
import br.com.samplegroup.transformer.JsonT

new TodoResource(new TodoDAO(), new JsonT())