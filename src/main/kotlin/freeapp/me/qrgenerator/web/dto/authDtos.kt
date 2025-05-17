package freeapp.me.qrgenerator.web.dto

import freeapp.me.qrgenerator.entity.User

data class SignUpDto(
    val email: String,
    val password: String
) {

    fun toEntity(encPassword: String): User {
        return User(
            email = email,
            username = "",
            password = encPassword,
            signType = User.SignType.EMAIL
        )
    }

}

data class LoginDto(
    val email: String,
    val password: String
)
