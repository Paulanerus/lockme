package pw.paul.ui.ux.font

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

val InterTypography = Typography(defaultFontFamily = interLightFontFamily())

private fun interLightFontFamily() = FontFamily(
    Font("assets/ux/font/Inter-Light.ttf", FontWeight.Light, FontStyle.Normal)
)
