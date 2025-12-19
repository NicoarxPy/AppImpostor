package com.example.impostor.ui.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = 1,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    var mergedStyle = style
    if (fontSize != TextUnit.Unspecified) mergedStyle = mergedStyle.copy(fontSize = fontSize)
    if (fontWeight != null) mergedStyle = mergedStyle.copy(fontWeight = fontWeight)
    if (letterSpacing != TextUnit.Unspecified) mergedStyle = mergedStyle.copy(letterSpacing = letterSpacing)
    if (color != Color.Unspecified) mergedStyle = mergedStyle.copy(color = color)

    var scaledTextStyle by remember(text, mergedStyle) { mutableStateOf(mergedStyle) }
    var isCalculating by remember(text, mergedStyle) { mutableStateOf(true) }

    val textColor = if (isCalculating) Color.Transparent else mergedStyle.color

    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        maxLines = maxLines,
        style = scaledTextStyle,
        softWrap = false,
        onTextLayout = { textLayoutResult ->
            if (isCalculating && textLayoutResult.didOverflowWidth) {
                val sourceSize = scaledTextStyle.fontSize
                if (sourceSize == TextUnit.Unspecified) {
                    // If we don't have a size, we can't scale. Stop trying.
                    isCalculating = false
                } else {
                    scaledTextStyle = scaledTextStyle.copy(fontSize = sourceSize * 0.95f)
                }
            } else {
                isCalculating = false
            }
        }
    )
}
