package br.com.messageproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageProducerApplication

fun main(args: Array<String>) {
    runApplication<MessageProducerApplication>(*args)
}
