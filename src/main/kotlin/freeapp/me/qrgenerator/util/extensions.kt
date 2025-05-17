package freeapp.me.qrgenerator.util

import com.linecorp.kotlinjdsl.render.jpql.JpqlRendered
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun <T : Any> EntityManager.getSingleResultOrNull(
    render: JpqlRendered,
    classType: Class<T>
): T? {

    val typedQuery = this.createQuery(render.query, classType).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    return try {
        typedQuery.singleResult
    } catch (e: NoResultException) {
        null
    }

}





inline fun <reified T : Any> EntityManager.getResult(
    render: JpqlRendered,
    classType: Class<T>
): MutableList<T> {

    val typedQuery = this.createQuery(render.query, classType).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }
    return typedQuery.resultList
}




fun EntityManager.getCountByQuery(
    render: JpqlRendered,
): Long {

    // 에러 발생 위험.
    val countQuery = render.query.replace(
        Regex("^select\\s+.+?from\\s+", RegexOption.IGNORE_CASE),
        "select count(*) from "
    )

    val fetch = this.createQuery(countQuery, Long::class.java).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    return fetch.singleResult ?: 0L
}


inline fun <reified T : Any> EntityManager.getResultWithPagination(
    render: JpqlRendered,
    type: Class<T>,
    pageable: Pageable
): MutableList<T> {

    val fetch = this.createQuery(render.query, type).apply {
        render.params.forEach { name, value ->
            setParameter(name, value)
        }
    }

    fetch.firstResult = pageable.offset.toInt()
    fetch.maxResults = pageable.pageSize

    return fetch.resultList
}



fun LocalDateTime.toStringbyFormat(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val dateTimeFormatter =
        DateTimeFormatter.ofPattern(pattern)
    return dateTimeFormatter.format(this).toString()
}
