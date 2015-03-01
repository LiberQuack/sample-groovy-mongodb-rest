package br.com.samplegroup.dao

import com.google.gson.Gson
import com.mongodb.DB
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.WriteConcern
import org.jongo.*

abstract class DAO {

    Jongo db
    MongoCollection defaultCollection
    Gson gson = new Gson()

    DAO() {
        def url = System.getenv("DB_URL") ?: "mondob://127.0.0.1/todo-app"
        def uri = new MongoClientURI(url)
        DB mongDB = new MongoClient(uri).getDB((((url =~ /(?:\/\/.+\/)(.+)/)[0])[1])) //Todo: KISS
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