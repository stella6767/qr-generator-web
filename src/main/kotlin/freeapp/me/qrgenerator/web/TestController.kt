package freeapp.me.qrgenerator.web

import freeapp.me.qrgenerator.service.MailService
import freeapp.me.qrgenerator.web.dto.EmailBodyDto
import freeapp.me.qrgenerator.web.dto.EmailDto
import freeapp.me.qrgenerator.web.dto.EmailTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
    private val mailService: MailService
) {

    @GetMapping("/simple")
    fun sendSimpleMailMessage() {



        mailService.sendEmailTemplate("ㅁㄴㅇㅁㄴㅇ@naver.com", EmailDto(
            emailTemplate = EmailTemplate.VERIFICATION,
            body = EmailBodyDto()
        ))
    }

}
