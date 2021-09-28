package pw.paul

import pw.paul.config.repository.ConfigRepository
import pw.paul.lang.Translator

fun main() {
    Translator.collectLanguages()
    ConfigRepository.load()

    Runtime.getRuntime().addShutdownHook(
        Thread {
            ConfigRepository.save()
        }
    )
}
