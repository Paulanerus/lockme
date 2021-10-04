package pw.paul.services.io

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import pw.paul.security.model.EncryptedEntry
import pw.paul.services.encryption.decrypt
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.CopyOnWriteArraySet
import kotlin.io.path.*

const val FILE_SUFFIX = ".lockme"

val appDir = dir()

val vaultDir = appDir / "Vault"

val keyFile = appDir / "user.key"

val configFile = appDir / "config.json"

val vaultItems = CopyOnWriteArraySet<EncryptedEntry>()

val mapper = jacksonObjectMapper().apply {
    enable(SerializationFeature.INDENT_OUTPUT)
}

private fun dir(): Path {
    val path = if (System.getProperty("lockme.dir")
        .isNullOrBlank()
    ) System.getenv("APPDATA") else System.getProperty("lockme.dir")

    return Path(path) / "LockMe"
}

fun collectItems() {
    Files.list(vaultDir).forEach { path ->
        val entry = readEntry(path)

        entry?.let { vaultItems.add(it) }
    }
}

fun writeEntry(entry: EncryptedEntry) {
    val path = entry.toPath()

    if (path.notExists()) path.createFile()

    path.writeBytes(entry.toBytes())
}

fun readEntry(path: Path): EncryptedEntry? {
    if (path.notExists()) return null

    val data = path.readBytes()

    if (data.size < 12) return null

    val iv = data.copyOf(12)
    val entryBytes = decrypt(data.copyOfRange(12, data.size), iv)

    return mapper.readValue<EncryptedEntry>(entryBytes)
}
