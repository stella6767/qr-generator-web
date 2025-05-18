package freeapp.me.qrgenerator.web

import freeapp.me.qrgenerator.config.UserPrincipal
import freeapp.me.qrgenerator.service.sign.SignService
import freeapp.me.qrgenerator.web.dto.LoginDto
import freeapp.me.qrgenerator.web.dto.SignUpDto
import freeapp.me.qrgenerator.web.dto.VerifyDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
    private val signService: SignService,
) {


    @GetMapping("/sign-up")
    fun signUpPage(
        model: Model,
    ): String {
        model.addAttribute("isSignUp", true)
        return "page/sign"
    }

    @GetMapping("/login")
    fun loginPage(
        model: Model,
    ): String {
        model.addAttribute("isSignUp", false)
        return "page/sign"
    }


    @PostMapping("/login")
    fun login(
        model: Model,
        loginDto: LoginDto,
        httpRequest: HttpServletRequest,
    ): String {

        signService.login(loginDto, httpRequest)

        return "redirect:/"
    }


    @PostMapping("/sign-up")
    fun signUp(
        model: Model,
        @Valid signUpDto: SignUpDto,
    ): String {

        val dto = signService.signUp(signUpDto)

        model.addAttribute("email", dto.email)
        model.addAttribute("token", dto.token)
        model.addAttribute("expireMinute", dto.expireMinute)

        return "page/verify"
    }


    @PostMapping("/verify-code")
    fun verify(
        model: Model,
        verifyDto: VerifyDto,
        httpRequest: HttpServletRequest
    ): String {

        signService.signUpWithEmailVerify(verifyDto, httpRequest)

        return "page/index"
    }


}
