package jp.iwanagat85.controller.rest

import jp.iwanagat85.domain.MessageService
import jp.iwanagat85.exception.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MessageController @Autowired
constructor(private val messageService: MessageService) {

    private val logger = LoggerFactory.getLogger(MessageController::class.java)

    @RequestMapping(path = ["/messages"], method = [RequestMethod.GET])
    fun getAll(): ResponseEntity<*> {
        return try {
            val response = messageService.findAll()
            ResponseEntity(response, HttpStatus.CREATED)
        } catch (e: Exception) {
            logger.error(e.message, e)
            ResponseEntity<String>(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @RequestMapping(path = ["/messages/{id}"], method = [RequestMethod.GET])
    fun getById(@PathVariable("id") id: String): ResponseEntity<*> {
        return try {
            val response = messageService.findById(id)
            ResponseEntity(response, HttpStatus.CREATED)
        } catch (e: NotFoundException) {
            logger.error(e.message, e)
            ResponseEntity<String>(e.message, HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            logger.error(e.message, e)
            ResponseEntity<String>(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @RequestMapping(path = ["/messages"], method = [RequestMethod.POST])
    fun register(@RequestBody request: RegisterMessageRequest): ResponseEntity<*> {
        return try {
            messageService.register(request)
            ResponseEntity<Any>(HttpStatus.CREATED)
        } catch (e: Exception) {
            logger.error(e.message, e)
            ResponseEntity<String>(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
