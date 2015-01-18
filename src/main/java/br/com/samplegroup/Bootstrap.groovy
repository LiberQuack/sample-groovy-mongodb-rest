package br.com.samplegroup

import br.com.samplegroup.controller.TodoResource

class Bootstrap {

    public static void main(String[] args) {
        new TodoResource().setup()
    }

}
