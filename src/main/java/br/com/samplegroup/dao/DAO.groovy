package br.com.samplegroup.dao

import com.google.gson.Gson
import com.mongodb.*
import org.jongo.Jongo
import org.jongo.MongoCollection

abstract class DAO {

    Jongo db
    MongoCollection defaultCollection

    DAO() {
        DB mongDB
        def url = System.getenv("DB_URL")
        def dbName = System.getenv("DB_NAME")
        if (url && dbName) {
            def uri = new MongoClientURI(url)
            mongDB = new MongoClient(uri).getDB(dbName)
        } else {
            def host = "127.0.0.1"
            def uri = new MongoClientURI("mongodb://$host")
            mongDB = new MongoClient(uri).getDB("todo-app")
            System.err.println("WARNING: MAKE SURE YOU RUN [vagrant up] BEFORE STARTING THE APP")
            System.err.println("WARNING: INVALID ENVS DB_URL && DB_NAME, RUNNING AGAINST $host")
        }
        mongDB.setWriteConcern(WriteConcern.SAFE)
        this.db = new Jongo(mongDB)
    }

    List<?> findAll() {
        this.defaultCollection.find().as(HashMap).asList()
    }

    Object remove(Object obj) {
        if (!obj._id) {
            throw new Exception("The Object doesn't have '_id'") //Todo: Need to create a customized Exception
        }
        this.defaultCollection.remove("{'_id': '$obj._id'}")
    }

    Object findOne(Object obj) {
        this.defaultCollection.findOne(obj)
    }

    List<?> findAll(Object obj) {
        this.defaultCollection.find(toJson(obj)).as(HashMap).asList()
    }

    Object save(Object obj) {
        this.defaultCollection.save(obj)
        return obj
    }

    protected String toJson(Object obj) {
        def gson = new Gson()
        def json = gson.toJson(obj)
        def hashMap = gson.fromJson(json, HashMap)
        gson.toJson(hashMap.findAll { it.value }) //Todo: Find all should filter sub maps too
    }
}
