package freeapp.me.qrgenerator.util

import java.security.SecureRandom
import java.util.*


private const val VERIFY_CODE_INT_LENGTH = 6
private const val VERIFY_TOKEN_BYTES = 20

fun generateVerifyToken(bytesLength: Int = VERIFY_TOKEN_BYTES): String {
    val random = SecureRandom()
    val bytes = ByteArray(bytesLength)
    random.nextBytes(bytes)
    val encoder = Base64.getUrlEncoder().withoutPadding()
    return encoder.encodeToString(bytes)
}


fun generateVerifyCode(): String {
    // 6자리 랜덤한 숫자문자열 생성
    return getIntRandomString(VERIFY_CODE_INT_LENGTH)
}

private fun getIntRandomString(targetStringLength: Int): String {
    val leftLimit = 48 // numeral '0'
    val rightLimit = 57 // letter '9'

    val random = Random()
    return random.ints(leftLimit, rightLimit + 1)
        .filter { i: Int -> i <= 57 || i >= 65 }
        .limit(targetStringLength.toLong())
        .collect(
            { StringBuilder() },
            { obj: StringBuilder, codePoint: Int -> obj.appendCodePoint(codePoint) }
        ) { obj: StringBuilder, s: StringBuilder ->
            obj.append(
                s
            )
        }
        .toString()
}
