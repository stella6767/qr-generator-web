package freeapp.me.qrgenerator.repo

import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import freeapp.me.qrgenerator.entity.User
import freeapp.me.qrgenerator.util.getResult
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>, UserCustomRepository {

}


interface UserCustomRepository {

    fun findUserByEmailAndSignTypeAndStatus(
        email: String, signType: User.SignType, status: User.Status
    ): MutableList<User>
}


class UserCustomRepositoryImpl(
    private val renderer: JpqlRenderer,
    private val ctx: JpqlRenderContext,
    private val em: EntityManager,
) : UserCustomRepository {


    override fun findUserByEmailAndSignTypeAndStatus(
        email: String,
        signType: User.SignType,
        status: User.Status
    ): MutableList<User> {

        val query = jpql {
            select(
                entity(User::class),
            ).from(
                entity(User::class),
            ).where(
                and(
                    path(User::email).equal(email.trim()),
                    path(User::status).equal(status),
                    path(User::signType).equal(signType),
                )
            )
        }

        val render = renderer.render(query = query, ctx)
        return em.getResult(render, User::class.java)
    }


}
