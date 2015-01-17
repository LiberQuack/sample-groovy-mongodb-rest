package br.com.samplegroup.dao

import com.mongodb.DBCollection

class TodoDao extends DAO{

    DBCollection collection

    TodoDao() {
        super()
        this.collection = db.getCollection("todo")
    }

    @Override
    Object insert(Object obj) {
        def doc = toBasicDBObject(obj)
        collection.insert(doc)
    }

    @Override
    Object find(Object obj) {
        return null
    }

    @Override
    Object findAll(Object obj) {
        return null
    }

    @Override
    Object remove(Object obj) {
        return null
    }

    @Override
    Object findAndRemove(Object obj) {
        return null
    }

    @Override
    Object update(Object obj) {
        return null
    }
}
