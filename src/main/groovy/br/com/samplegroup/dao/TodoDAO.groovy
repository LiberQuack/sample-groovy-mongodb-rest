package br.com.samplegroup.dao

import br.com.samplegroup.interfaces.IDAO
import br.com.samplegroup.model.Todo

class TodoDAO implements IDAO {

    DAO dao

    TodoDAO() {
        this.dao = new DAO().db.getCollection("todo")
        dao.collection.ensureIndex("{title: 'text', description: 'text'}")
    }

    @Override
    List<Todo> findAll() {
        dao.findAll().limit(100).as(Todo).toList()
    }

    @Override
    List<Todo> findAll(Map exactFieldsSearch) {
        dao.findAll(exactFieldsSearch).limit(100).as(Todo).toList()
    }

    @Override
    List<Todo> findInIndexedTexts(String search) {
        dao.findInIndexedTexts(search).limit(100).as(Todo).toList()
    }

    @Override
    Todo findOne(String _id) {
        dao.findOne(_id).as(Todo)
    }

    @Override
    Todo remove(String _id) {
        dao.remove(_id)
    }

    @Override
    Todo save(Object obj) {
        dao.save(obj)
    }
}
