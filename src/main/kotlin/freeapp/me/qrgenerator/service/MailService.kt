package freeapp.me.qrgenerator.service

import jakarta.mail.internet.MimeMessage
import mu.KotlinLogging
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service


@Service
class MailService(
    private val mailSender: JavaMailSender
) {

    private val log = KotlinLogging.logger {}

    fun sendSimpleMailMessage() {
        val simpleMailMessage = SimpleMailMessage()
        // 메일을 받을 수신자 설정
        simpleMailMessage.setTo("test@naver.com")
        // 메일의 제목 설정
        simpleMailMessage.subject = "테스트 메일 제목"
        // 메일의 내용 설정
        simpleMailMessage.text = "테스트 메일 내용"
        mailSender.send(simpleMailMessage)
        log.info("메일 발송 성공!")
    }


}
