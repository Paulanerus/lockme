package pw.paul.config.model

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.annotation.JsonIgnore

sealed class Setting {

    data class BoolSetting(val title: String, var value: Boolean, @JsonIgnore val default: Boolean) : Setting()

    @Composable
    fun handleInUI(setting: Setting) {
        when (setting) {
            is BoolSetting -> {
            }
        }
    }
}
