package freeapp.me.qrgenerator.web

import freeapp.me.qrgenerator.config.UserPrincipal
import freeapp.me.qrgenerator.service.UserService
import freeapp.me.qrgenerator.web.dto.UpdateProfileDto
import freeapp.me.qrgenerator.web.dto.UserDeleteRequestDto
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRedirectView
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRefreshView
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping


@Controller
class UserController(
    private val userService: UserService,
) {

    @HxRequest
    @DeleteMapping("/user")
    fun deleteUser(
        @AuthenticationPrincipal principal: UserPrincipal,
        deleteRequestDto: UserDeleteRequestDto,
    ): HtmxRedirectView {

        userService.deleteUser(
            principal.user.id,
            deleteRequestDto
        )
        return HtmxRedirectView("/")
    }


    @HxRequest
    @PutMapping("/user")
    fun updateUser(
        @AuthenticationPrincipal principal: UserPrincipal,
        profileDto: UpdateProfileDto,
        model: Model,
    ): HtmxRefreshView {

        val user =
            userService.updateUser(principal.user.id, profileDto)

        //model.addAttribute("user", user)

        return HtmxRefreshView()
    }


}
