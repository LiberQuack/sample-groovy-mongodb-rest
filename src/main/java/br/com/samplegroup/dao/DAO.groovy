package br.com.samplegroup.dao

import com.google.gson.Gson
import com.mongodb.*
import org.jongo.Find
import org.jongo.FindOne
import org.jongo.Jongo
import org.jongo.MongoCollection
import org.jongo.MongoCursor
import org.jongo.Oid

abstract class DAO {

    Jongo db
    MongoCollection defaultCollection
    Gson gson = new Gson()

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
            System.err.println("WARNING: INVALID ENVS DB_URL && DB_NAME, RUNNING AGAINST $host")
        }
        mongDB.setWriteConcern(WriteConcern.SAFE)
        this.db = new Jongo(mongDB)
    }

    Find findAll() {
        def Cursor = this.defaultCollection.find()
    }

    Find findAll(Map exactFieldsSearch) {
        this.defaultCollection.find(toJson(exactFieldsSearch))
    }

    Find findInIndexedTexts(String search) {
        this.defaultCollection.find("{\$text: {\$search: \"$search\"}}")
    }

    FindOne findOne(String _id) {
        this.defaultCollection.findOne(Oid.withOid(_id))
    }

    Object remove(String _id) {
        this.defaultCollection.remove(Oid.withOid(_id))
    }

    Object save(Object obj) {
        this.defaultCollection.save(obj)
        return obj
    }

    protected String toJson(Object obj) {
        def json = gson.toJson(obj)
        def hashMap = gson.fromJson(json, HashMap)
        gson.toJson(hashMap.findAll { it.value }) //Todo: Find all should filter sub maps too
    }
}