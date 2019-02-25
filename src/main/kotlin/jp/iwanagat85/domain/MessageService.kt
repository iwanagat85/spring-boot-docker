package jp.iwanagat85.domain

import jp.iwanagat85.controller.rest.MessageResponse
import jp.iwanagat85.controller.rest.RegisterMessageRequest
import jp.iwanagat85.data.db.MessageRepository
import jp.iwanagat85.data.db.entity.Message
import jp.iwanagat85.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class MessageService @Autowired
constructor(private val messageRepository: MessageRepository) {

    fun register(request: RegisterMessageRequest) {
        messageRepository.save(Message(message = request.message))
    }

    fun findAll(): List<MessageResponse> {
        return messageRepository.findAll()
            .stream()
            .map {
                MessageResponse(it.id!!, it.message)
            }
            .collect(Collectors.toList())
    }

    fun findById(id: String): MessageResponse {
        return messageRepository.findById(id)
            .map {
                MessageResponse(it.id!!, it.message)
            }
            .orElseThrow<NotFoundException> { NotFoundException() }
    }
}
