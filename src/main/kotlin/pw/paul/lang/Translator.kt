package pw.paul.lang

import java.io.File

object Translator {
    private const val LANG_SUFFIX = ".lang"

    const val DEFAULT_LANG = "en_US"

    private val languages = HashMap<String, Map<String, String>>()

    var currentLang = "de_DE"

    fun collectLanguages() {
        val url = Translator::class.java.getResource("/assets/ux/lang")

        url?.let {
            val dir = File(url.file)

            if (dir.exists()) {
                dir.walk()
                    .filter { it.isFile && it.name.endsWith(LANG_SUFFIX) }
                    .forEach { file ->
                        val keyName = file.name.dropLast(5)

                        val translations = mutableMapOf<String, String>()

                        file.readLines()
                            .filter { it.contains("=") }
                            .map { line -> line.split("=") }
                            .forEach { line -> translations[line[0]] = line[1] }

                        languages[keyName] = translations
                    }
            }
        }

        println("${languages.size} language files were found!")
    }

    fun from(value: String): String {
        return languages[currentLang]!![value] ?: value
    }
}
