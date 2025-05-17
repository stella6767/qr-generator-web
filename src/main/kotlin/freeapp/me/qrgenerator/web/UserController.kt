package freeapp.me.qrgenerator.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController(

) {


    @GetMapping("/sign-up")
    fun signUp(
        model: Model,
    ): String {
        model.addAttribute("isSignUp", true)
        return "page/sign"
    }

    @GetMapping("/login")
    fun login(
        model: Model,
    ): String {
        model.addAttribute("isSignUp", false)
        return "page/sign"
    }


}
