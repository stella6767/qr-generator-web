package freeapp.me.qrgenerator.service

import freeapp.me.qrgenerator.web.dto.EmailDto
import gg.jte.TemplateEngine
import gg.jte.output.StringOutput
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import mu.KotlinLogging
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.time.LocalDateTime


@Service
class MailService(
    private val mailSender: JavaMailSender,
    private val templateEngine: TemplateEngine,
) {

    private val log = KotlinLogging.logger {}

    fun sendSimpleMailMessage(
        to:String,
        emailDto: EmailDto
    ) {
        val simpleMailMessage =
            SimpleMailMessage()
        simpleMailMessage.from = "\"QR Code App\" <your-gmail@gmail.com>"
        // 메일을 받을 수신자 설정
        simpleMailMessage.setTo(to)
        // 메일의 제목 설정
        simpleMailMessage.subject = emailDto.emailTemplate.subject
        // 메일의 내용 설정
        simpleMailMessage.text = "test"
        mailSender.send(simpleMailMessage)
        log.info("메일 발송 성공!")
    }

    @Async
    fun sendEmailTemplate(
        to: String,
        emailDto: EmailDto,
    ) {

        val output = StringOutput()
        //메일로 전송하면 인라인 방식의 css만 적용된다?
        templateEngine.render(emailDto.emailTemplate.templateTarget, emailDto.body, output)

        val mimeMessage =
            mailSender.createMimeMessage()

        val helper =
            MimeMessageHelper(mimeMessage, true, Charsets.UTF_8.name())

        helper.setSubject(emailDto.emailTemplate.subject)
        helper.setFrom(InternetAddress("test@gmail.com", "QR Generator"))

        helper.setTo(to)
        helper.setText(output.toString(), true)

        mailSender.send(mimeMessage)
    }




}
