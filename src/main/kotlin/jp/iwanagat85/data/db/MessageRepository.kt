package jp.iwanagat85.data.db

import jp.iwanagat85.data.db.entity.Message
import org.springframework.data.mongodb.repository.MongoRepository

interface MessageRepository : MongoRepository<Message, String>
