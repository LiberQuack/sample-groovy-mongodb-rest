package br.com.samplegroup.transformer

import br.com.samplegroup.interfaces.Transformer
import com.google.gson.Gson

/**
 * Created by martins on 1/21/2015.
 */
class JsonT implements Transformer{

    Gson gson = new Gson()

    @Override
    Object unrender(Object message, Class classOf) {
        gson.fromJson(message, classOf)
    }

    @Override
    String render(Object model) throws Exception {
        gson.toJson(model)
    }
}
