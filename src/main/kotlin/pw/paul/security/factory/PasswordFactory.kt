package pw.paul.security.factory

import java.security.SecureRandom

object PasswordFactory {

    private const val SIMPLE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    private const val NUMBER_ALPHABET = "1234567890"

    private const val SPECIAL_ALPHABET = "!\"#$%&()*+-/:;<>?@[]{}"

    private const val SIMILAR_ALPHABET = "iIl1LoO0"

    fun build(length: Int, lower: Boolean, numbers: Boolean, special: Boolean, excludeSimilar: Boolean): String {
        val random = SecureRandom()

        var password = SIMPLE_ALPHABET

        if (lower) password += SIMPLE_ALPHABET.lowercase()

        if (numbers) password += NUMBER_ALPHABET

        if (special) password += SPECIAL_ALPHABET

        if (excludeSimilar) SIMILAR_ALPHABET.forEach { password = password.replace(it.toString(), "") }

        return (0..length).map { password[random.nextInt(password.length)] }.joinToString(separator = "")
    }
}
