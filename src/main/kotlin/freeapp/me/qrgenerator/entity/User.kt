package freeapp.me.qrgenerator.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "user")
class User(
    username: String,
    email: String,
    password: String,
    role: Role = Role.USER,
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

    @Column(name = "last_login_date")
    var lastLoginDate = lastLoginDate


    fun updateLastLoginDate(time: LocalDateTime = LocalDateTime.now()) {
        this.lastLoginDate = time
    }

    enum class Role(
        val value:String
    ) {

        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN"),
    }

}
