package br.com.samplegroup.dao

class TodoDAO extends DAO {

    TodoDAO() {
        super()
        this.defaultCollection = db.getCollection("todo")
        this.defaultCollection.ensureIndex("{title: 'text', description: 'text'}")
    }

}
