package freeapp.me.qrgenerator.util

import freeapp.me.qrgenerator.config.UserPrincipal
import freeapp.me.qrgenerator.web.dto.UserResponseDto
import org.springframework.security.core.context.SecurityContextHolder

fun getCurrentUser(): UserResponseDto? {
    try {
        val auth =
            SecurityContextHolder.getContext().authentication

        println(auth.isAuthenticated)
        println(auth.principal)

        if (auth != null && auth.isAuthenticated &&
            auth.principal != "anonymousUser" &&
            auth.principal is UserPrincipal
        ) {
            println("User not found")

            val userPrincipal = auth.principal as UserPrincipal
            return UserResponseDto.fromEntity(userPrincipal.user)
        }
        println("User not found")

    } catch (e: Exception) {
        println("사용자 정보 가져오기 실패: ${e.message}")
    }
    return null
}

fun isLoggedIn(): Boolean {

    println("?????")

    return getCurrentUser() != null
}
