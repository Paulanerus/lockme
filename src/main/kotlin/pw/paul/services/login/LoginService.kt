package pw.paul.services.login

import com.lambdaworks.crypto.SCryptUtil
import pw.paul.security.key.KeyService
import pw.paul.services.io.collectItems
import pw.paul.services.io.keyFile
import kotlin.io.path.notExists
import kotlin.io.path.readText

fun loginUser(password: String): Boolean {
    if (keyFile.notExists() || password.isEmpty()) return false

    if (SCryptUtil.check(password, keyFile.readText())) {
        KeyService.generate(password)

        collectItems()

        return true
    }

    return false
}
