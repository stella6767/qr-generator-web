package freeapp.me.qrgenerator.web

import freeapp.me.qrgenerator.service.MailService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
    private val mailService: MailService
) {

    @GetMapping("/simple")
    fun sendSimpleMailMessage() {
        mailService.sendSimpleMailMessage()
    }

}
