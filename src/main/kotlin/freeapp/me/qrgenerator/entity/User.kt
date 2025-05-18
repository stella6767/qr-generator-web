package freeapp.me.qrgenerator.entity

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime


@Entity
@Table(name = "user")
class User(
    socialId:String? = null,
    status: Status,
    username: String,
    email: String,
    password: String,
    role: Role = Role.USER,
    signType: SignType,
    lastLoginDate: LocalDateTime? = null,
) : BaseEntity() {

    @Column(name = "social_id")
    var socialId: String? = socialId

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


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Comment("ACTIVATED(\"활성\"), DELETED(\"탈퇴\"), DEACTIVATED(\"비활성\")")
    var status: Status = status

    @Column(name = "last_login_date")
    var lastLoginDate = lastLoginDate


    fun updateLastLoginDate(time: LocalDateTime = LocalDateTime.now()) {
        this.lastLoginDate = time
    }

    enum class Status(
        val displayName: String
    ) {
        ACTIVATED("활성"),
        DEACTIVATED("비활성"),
        DELETED("탈퇴"),
    }


    enum class Role(
        val value: String
    ) {

        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN"),
    }

    enum class SignType(
        val clientName: String,
        val authorizationUrl: String,
    ) {
        GOOGLE(
            "Google",
            "/oauth2/authorization/google"
        ),
        GITHUB(
            "GitHub",
            "/oauth2/authorization/github"
        ),
        EMAIL(
            "Email",
            "/oauth2/authorization/email"
        )
        ;

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
