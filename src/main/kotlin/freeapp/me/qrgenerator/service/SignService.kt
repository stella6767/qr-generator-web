package freeapp.me.qrgenerator.service

import freeapp.me.qrgenerator.entity.User
import freeapp.me.qrgenerator.entity.UserVerify
import freeapp.me.qrgenerator.repo.UserRepository
import freeapp.me.qrgenerator.repo.UserVerifyRepository
import freeapp.me.qrgenerator.util.generateVerifyCode
import freeapp.me.qrgenerator.util.generateVerifyToken
import freeapp.me.qrgenerator.web.dto.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class SignService(
    private val userRepository: UserRepository,
    private val userVerifyRepository: UserVerifyRepository,
    private val mailService: MailService,
    private val encoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }


    @Transactional
    fun signUp(signUpDto: SignUpDto) {

        checkIsNormalEmailAddressAvailable(signUpDto.email)

        val expireMinute: Long = 10

        val userVerify =
            userVerifyRepository.findLatestUserVerifyByEmail(signUpDto.email, LocalDateTime.now())
                ?: run {
                    val user =
                        userRepository.save(signUpDto.toEntity(encoder.encode(signUpDto.password)))
                    UserVerify(
                        user = user,
                        code = generateVerifyCode(),
                        verifyToken = generateVerifyToken(),
                        expiredAt = LocalDateTime.now().plusMinutes(expireMinute),
                    )
                }

        userVerifyRepository.save(userVerify)

        val emailDto = EmailDto(
            emailTemplate = EmailTemplate.VERIFICATION,
            body = EmailBodyDto(
                verificationCode = userVerify.code,
                validityMinutes = expireMinute.toString()
            )
        )

        mailService.sendEmailTemplate(signUpDto.email, emailDto)
    }


    @Transactional
    fun signUpWithEmailVerify(verifyDto: VerifyDto) {

        //code와 token은 유효해야한다.
        //code와 token으로 꺼낸 이메일을 조회했을때, 유저는 회원가입이 가능해야한다.
        //code와 token은 해당 유저를 위해 제일 마지막에 발급된 토큰이어야 한다.

        val verify = userVerifyRepository.findUserVerifyByCodeAndToken(
            verifyToken = verifyDto.token,
            verifyCode = verifyDto.code
        ) ?: throw IllegalArgumentException("EMAIL TOKEN IS UNAUTHORIZED")

        checkIsNormalEmailAddressAvailable(verify.user.email)

        verify.verify()
        //회원가입 성공
        verify.user.status = User.Status.ACTIVATED

    }


    private fun checkIsNormalEmailAddressAvailable(email: String) {
        //일반 회원가입 시(소셜 X) 해당 이메일로 이미 가입이 되어 있으면 불가능.
        val users =
            userRepository.findUserByEmailAndSignTypeAndStatus(
                email,
                User.SignType.EMAIL,
                User.Status.ACTIVATED
            )

        if (users.isNotEmpty()) {
            throw IllegalArgumentException("already exists email in database")
        }

    }


}
