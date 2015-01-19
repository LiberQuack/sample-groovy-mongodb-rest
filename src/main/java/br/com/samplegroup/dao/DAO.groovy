package br.com.samplegroup.dao

import com.mongodb.*
import org.jongo.Jongo

abstract class DAO {

    Jongo db

    abstract Object find(obj)
    abstract Object find()
    abstract Object remove(obj)
    abstract Object save(obj)

    DAO() {
        DB mongDB
        def url = System.getenv("DB_URL")
        def host = System.getenv("DB_HOST")
        def port = System.getenv("DB_PORT")
        def user = System.getenv("DB_USER")
        def psw = System.getenv("DB_PASSWORD")
        def dbName = System.getenv("DB_NAME")
        if (url) {
            def uri = new MongoClientURI(url)
            mongDB = new MongoClient(uri).getDB(dbName)
        } else {
            def cred = new MongoCredential("PLAIN", user, dbName, psw.toCharArray())
            def server = new ServerAddress(host, port)
            mongDB = new MongoClient(server, Arrays.asList(cred))
        }
        mongDB.setWriteConcern(WriteConcern.SAFE)
        this.db = new Jongo(mongDB)
    }
}
