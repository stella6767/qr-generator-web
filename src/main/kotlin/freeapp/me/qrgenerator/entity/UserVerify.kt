package freeapp.me.qrgenerator.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "userVerify")
class UserVerify(
    code: String,
    expiredAt: LocalDateTime? = null,
    verifiedAt: LocalDateTime? = null,
) : BaseEntity() {

    @Column(name = "code")
    @Comment("이메일 발송용 인증용 code")
    var code: String? = code

    @Column(name = "expired_at")
    @Comment("인증 유효만료기간")
    var expiredAt: LocalDateTime? = expiredAt

    @Column(name = "verified_at")
    @Comment("인증 완료시간")
    var verifiedAt: LocalDateTime? = verifiedAt


}
