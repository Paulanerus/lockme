package pw.paul.security.key

import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object KeyService {

    private const val ALGORITHM = "PBKDF2WithHmacSHA256"

    private const val ITERATIONS = 65536

    private const val LENGTH = 256

    lateinit var secretKey: SecretKey

    fun generate(password: String) {
        val factory = SecretKeyFactory.getInstance(ALGORITHM)

        val tmpKey = factory.generateSecret(PBEKeySpec(password.toCharArray(), ByteArray(16), ITERATIONS, LENGTH))

        secretKey = SecretKeySpec(tmpKey.encoded, "AES")
    }

    fun notExists() = this::secretKey.isInitialized.not()
}
