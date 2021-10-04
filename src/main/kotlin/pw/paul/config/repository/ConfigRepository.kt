package pw.paul.config.repository

import com.fasterxml.jackson.module.kotlin.readValue
import pw.paul.config.model.Setting
import pw.paul.services.io.configFile
import pw.paul.services.io.mapper
import kotlin.io.path.createFile
import kotlin.io.path.notExists
import kotlin.io.path.readBytes

object ConfigRepository {

    val DARK_MODE: Setting.BoolSetting = Setting.BoolSetting("ui.darkmode", false, true)

    private var settings = mutableListOf(DARK_MODE)

    fun save() = mapper.writeValue(configFile.toFile(), settings)

    fun load() {
        if (configFile.notExists()) configFile.createFile()

        if (configFile.notExists().or(configFile.readBytes().isEmpty())) save()
        settings = mapper.readValue(configFile.toFile())
    }
}
