package pw.paul.services.clipboard

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

private val clipboard = Toolkit.getDefaultToolkit().systemClipboard

fun copyToClipboard(content: String) {
    val stringSelection = StringSelection(content)

    clipboard.setContents(stringSelection, stringSelection)
}

fun clearClipboard() = copyToClipboard("")
