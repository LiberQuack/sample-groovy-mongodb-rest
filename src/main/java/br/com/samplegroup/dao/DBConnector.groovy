package br.com.samplegroup.dao

import com.mongodb.DB
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress

class DBConnector {

    DB db

    DBConnector() {
        def url = System.getenv("DB_URL")
        def host = System.getenv("DB_HOST")
        def port = System.getenv("DB_PORT")
        def user = System.getenv("DB_USER")
        def psw = System.getenv("DB_PASSWORD")
        if (url) {
            def uri = new MongoClientURI(url)
            this.db = new MongoClient(uri)
        } else {
            def cred = new MongoCredential("PLAIN", user, "todoapp", psw.toCharArray())
            def server = new ServerAddress(host,port)
            def mongo = new MongoClient(server,Arrays.asList(cred))
        }
    }

}
