package pw.paul.security.model

import pw.paul.services.encryption.encrypt
import pw.paul.services.io.FILE_SUFFIX
import pw.paul.services.io.mapper
import pw.paul.services.io.vaultDir
import java.nio.file.Path
import java.security.SecureRandom
import java.util.*
import kotlin.io.path.div

data class EncryptedEntry(var name: String, var username: String, var password: String, var website: String) {

    private val uuid = UUID.nameUUIDFromBytes(toBytes()).toString()

    fun toBytes(): ByteArray {
        val iv = ByteArray(12)

        val secureRandom = SecureRandom()
        secureRandom.nextBytes(iv)

        return iv + encrypt(mapper.writeValueAsBytes(this), iv)
    }

    fun toPath(): Path = vaultDir / "$uuid$FILE_SUFFIX"

    override fun toString(): String {
        return "$name:$username:$password:$website"
    }
}
