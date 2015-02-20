package br.com.samplegroup.dao
//Todo: Move these methods to super DAO
class TodoDao extends DAO {

    TodoDao() {
        super()
        this.defaultCollection = db.getCollection("todo")
        this.defaultCollection.ensureIndex("{title: 1}")
    }

}
