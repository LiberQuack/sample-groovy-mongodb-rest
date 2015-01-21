package br.com.samplegroup.dao

import com.mongodb.*
import org.jongo.Jongo

abstract class DAO {

    Jongo db

    abstract Object find()
    abstract Object find(obj)
    abstract Object save(obj)
    abstract Object remove(obj)

    DAO() {
        DB mongDB
        def url = System.getenv("DB_URL")
        def dbName = System.getenv("DB_NAME")
        if (url && dbName) {
            def uri = new MongoClientURI(url)
            mongDB = new MongoClient(uri).getDB(dbName)
        } else {
            def uri = new MongoClientURI("mongodb://1.0.0.7")
            mongDB = new MongoClient(uri).getDB("todo-app")
            System.err.println("WARNING, ENVS DB_URL/DB_NAME NOT FOUND, RUNNING AGAINST 1.0.0.7")
        }
        mongDB.setWriteConcern(WriteConcern.SAFE)
        this.db = new Jongo(mongDB)
    }
}
