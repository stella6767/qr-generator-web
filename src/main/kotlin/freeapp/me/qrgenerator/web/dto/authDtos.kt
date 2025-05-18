package freeapp.me.qrgenerator.web.dto

import freeapp.me.qrgenerator.entity.User
import freeapp.me.qrgenerator.util.EMAIL_PATTERN
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignUpDto(
    @field:NotBlank(message = "email is required")
    @field:Email(regexp = EMAIL_PATTERN, message = "It's invalid email format")
    val email: String,
    val password: String
) {

    fun toEntity(encPassword: String): User {
        return User(
            email = email,
            username = "",
            password = encPassword,
            signType = User.SignType.EMAIL,
            status = User.Status.DEACTIVATED
        )
    }

}

data class LoginDto(
    val email: String,
    val password: String
)

data class VerifyDto(
    val code: String,
    val token: String,
)

data class SignUpResponseDto(
    val email: String,
    val expireMinute: Long,
    val token: String,
)




interface OAuth2UserInfo {

    val attributes: Map<String, Any?>

    val signType: User.SignType

    fun getSocialId(): String

    fun getSocialPictureUrl(): String?

    fun getEmail(): String?

}


data class GoogleAuth2UserInfo(
    override val attributes: Map<String, Any?>,
    override val signType: User.SignType = User.SignType.GOOGLE,
) : OAuth2UserInfo {

    override fun getSocialId(): String {
        return attributes.get("sub").toString()
    }
    override fun getSocialPictureUrl(): String? {
        return attributes.get("picture")?.toString()
    }
    override fun getEmail(): String? {
        return attributes.get("email")?.toString()
    }
}


data class GithubAuth2UserInfo(
    override val attributes: Map<String, Any?>,
    override val signType: User.SignType = User.SignType.GITHUB,
) : OAuth2UserInfo {

    override fun getSocialId(): String {
        return attributes["id"].toString()
    }
    override fun getSocialPictureUrl(): String? {
        return attributes["avatar_url"]?.toString()
    }
    override fun getEmail(): String? {
        return attributes["email"]?.toString()
    }
}
