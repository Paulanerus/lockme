package pw.paul.ui.ux.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val darkColorScheme = darkColors(
    primary = Color(0xFF202731),
    primaryVariant = Color(0xFF333F4D),
    secondary = Color.White,
    secondaryVariant = Color(0xFFBBBFC4),
    background = Color(-1),
    surface = Color(-1),
    error = Color(0xFFBe4825),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)

val lightColorScheme = lightColors()

@Composable
fun appColors(): Colors {
    return if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
}
