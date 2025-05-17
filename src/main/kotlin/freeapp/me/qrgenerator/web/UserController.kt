package freeapp.me.qrgenerator.web

import freeapp.me.qrgenerator.web.dto.SignUpDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(

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


    @PostMapping("/sign-up")
    fun signUp(
        signUpDto: SignUpDto
    ) {



    }


}
