package jp.iwanagat85.config

import com.mongodb.MongoClient
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import java.util.*

@Configuration
class MongoConfig(
    @Value("\${spring.data.mongodb.uri}") private val mongoUri: String,
    @Value("\${spring.data.mongodb.database}") private val dbName: String
) : AbstractMongoConfiguration() {

    override fun getDatabaseName(): String {
        return dbName
    }

    override fun mongoClient(): MongoClient {
        val addresses = mongoUri.split(",").dropLastWhile { it.isEmpty() }.toTypedArray()
        val servers = ArrayList<ServerAddress>()
        for (address in addresses) {
            address.split(':').let {
                servers.add(ServerAddress(it[0].trim(), it[1].trim().toInt()))
            }
        }
        return MongoClient(servers)
    }
}
