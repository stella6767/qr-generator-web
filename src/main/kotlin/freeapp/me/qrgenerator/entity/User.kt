package freeapp.me.qrgenerator.entity

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "user")
class User(
    username: String,
    email: String,
    password: String,
    role: Role = Role.USER,
    signType: SignType,
    lastLoginDate: LocalDateTime? = null,
) : BaseEntity() {

    @Column(nullable = false)
    val username = username

    @Column(nullable = false, length = 100)
    val email = email

    @Column(nullable = false, length = 100)
    val password = password

    @Enumerated(EnumType.STRING)
    val role = role

    @Enumerated(EnumType.STRING)
    val signType = signType

    @Column(name = "last_login_date")
    var lastLoginDate = lastLoginDate


    fun updateLastLoginDate(time: LocalDateTime = LocalDateTime.now()) {
        this.lastLoginDate = time
    }

    enum class Role(
        val value: String
    ) {

        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN"),
    }

    enum class SignType() {
        GOOGLE,
        GITHUB,
        EMAIL;

        companion object {
            @JsonCreator
            fun from(str: String): SignType? {
                return SignType.values().firstOrNull {
                    it.name == str.uppercase()
                } ?: throw RuntimeException("unsupported signtype")
            }
        }
    }

}
