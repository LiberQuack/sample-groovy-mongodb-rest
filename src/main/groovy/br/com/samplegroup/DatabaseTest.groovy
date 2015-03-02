package br.com.samplegroup

import br.com.samplegroup.dao.TodoDAO

//Setup
def dao = new TodoDAO()
println "Starting"
def start = System.currentTimeMillis()

//Execution
println dao.defaultCollection.count()

//End
def end = System.currentTimeMillis()
println "Total Time: ${(end - start) / 1000}s"