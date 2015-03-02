package br.com.samplegroup.dao

import br.com.samplegroup.interfaces.IDAO
import com.google.gson.Gson
import com.mongodb.DB
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.WriteConcern
import org.jongo.*

class DAO implements IDAO{

    Jongo db
    MongoCollection collection
    Gson gson = new Gson()

    DAO() {
        def url = System.getenv("DB_URL") ?: "mongodb://127.0.0.1/todo-app"
        def uri = new MongoClientURI(url)
        DB mongDB = new MongoClient(uri).getDB((((url =~ /(?:\/\/.+\/)(.+)/)[0])[1])) //Todo: KISS
        mongDB.setWriteConcern(WriteConcern.SAFE)
        this.db = new Jongo(mongDB)
    }

    DAO(String collection) {
        DAO()
        this.collection = db.getCollection(collection)
    }

    Find findAll() {
        def Cursor = this.collection.find()
    }

    Find findAll(Map exactFieldsSearch) {
        this.collection.find(toJson(exactFieldsSearch))
    }

    Find findInIndexedTexts(String search) {
        this.collection.find("{\$text: {\$search: \"$search\"}}") //TODO: Need to use namedParameters
    }

    FindOne findOne(String _id) {
        this.collection.findOne(Oid.withOid(_id))
    }

    Object remove(String _id) {
        this.collection.remove(Oid.withOid(_id))
    }

    Object save(Object obj) {
        this.collection.save(obj)
        return obj
    }

    protected String toJson(Object obj) {
        def json = gson.toJson(obj)
        def hashMap = gson.fromJson(json, HashMap)
        gson.toJson(hashMap.findAll { it.value }) //Todo: Find all should filter sub maps too
    }
}