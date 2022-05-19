package br.com.messageproducer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class Controller {

    @GetMapping
    fun login(): String {
        return "Login success"
    }
}
