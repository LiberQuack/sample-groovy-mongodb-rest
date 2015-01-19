package br.com.samplegroup.dao

import com.google.gson.Gson
import com.mongodb.*

abstract class DAO {

    DB db

    abstract Object insert(obj)
    abstract Object find(obj)
    abstract Object findAll()
    abstract Object remove(obj)
    abstract Object findAndRemove(obj)
    abstract Object update(obj)

    DAO() {
        def url = System.getenv("DB_URL")
        def host = System.getenv("DB_HOST")
        def port = System.getenv("DB_PORT")
        def user = System.getenv("DB_USER")
        def psw = System.getenv("DB_PASSWORD")
        def dbName = System.getenv("DB_NAME")
        if (url) {
            def uri = new MongoClientURI(url)
            this.db = new MongoClient(uri).getDB(dbName)
        } else {
            def cred = new MongoCredential("PLAIN", user, dbName, psw.toCharArray())
            def server = new ServerAddress(host, port)
            this.db = new MongoClient(server, Arrays.asList(cred))
        }
        db.setWriteConcern(WriteConcern.SAFE)
    }

    /**
     * Transform any object into an BasicDBObject
     *
     * @param object
     * @return BasicDBObject - The mongoDB document
     */
    BasicDBObject toBasicDBObject(Object object) {
        def gson = new Gson()
        def map = gson.fromJson(gson.toJson(object), HashMap.class)
        return new BasicDBObject(map)
    }
}
