package pw.paul.services.encryption

import pw.paul.security.key.KeyService
import java.lang.IllegalStateException
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec

private const val ALGORITHM = "AES/GCM/NoPadding"

private val cipher = Cipher.getInstance(ALGORITHM)

fun encrypt(data: ByteArray, iv: ByteArray): ByteArray {
    if (KeyService.notExists()) throw IllegalStateException("Secret does not exist")

    cipher.init(Cipher.ENCRYPT_MODE, KeyService.secretKey, GCMParameterSpec(128, iv))

    return cipher.doFinal(data)
}

fun decrypt(data: ByteArray, iv: ByteArray): ByteArray {
    if (KeyService.notExists()) throw IllegalStateException("Secret does not exist")

    cipher.init(Cipher.DECRYPT_MODE, KeyService.secretKey, GCMParameterSpec(128, iv))

    return cipher.doFinal(data)
}
