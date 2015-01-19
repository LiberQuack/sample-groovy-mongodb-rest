package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource
import com.google.gson.Gson

class Bootstrap {

    public static void main(String[] args) {
        def map = [name: "Thiago Martins"]
        map.age = 21
        map.address = [:]
        map.address.number = 5
        map.address.city = "Suzano"

        println new Gson().toJson(map)
        new TodoResource().setup()
    }

}
