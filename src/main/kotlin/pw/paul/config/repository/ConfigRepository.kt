package pw.paul.config.repository

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import pw.paul.config.model.Setting
import kotlin.io.path.*

object ConfigRepository {

    val DARK_MODE: Setting.BoolSetting = Setting.BoolSetting("ui.darkmode", false, true)

    private var settings = mutableListOf(DARK_MODE)

    val homeDir = (Path(System.getenv("APPDATA")) / "LockMe").apply {
        if (notExists()) createDirectory()
    }

    private val configFile = (homeDir / "Config.json")

    private val mapper = jacksonObjectMapper().apply {
        enable(SerializationFeature.INDENT_OUTPUT)
    }

    fun save() = mapper.writeValue(configFile.toFile(), settings)

    fun load() {
        if (configFile.notExists()) configFile.createFile()

        if (configFile.notExists().or(configFile.readBytes().isEmpty())) save()
        settings = mapper.readValue(configFile.toFile())
    }
}
