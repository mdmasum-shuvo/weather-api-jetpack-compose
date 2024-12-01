package com.masum.weather.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masum.weather.ui.theme.Purple40
import com.masum.weather.ui.theme.Purple80
import com.masum.weather.ui.theme.grayRowColor
import com.masum.weather.ui.theme.gray_bg
import com.masum.weather.ui.theme.light_gray
import com.masum.weather.ui.theme.text_gray
import kotlinx.coroutines.delay

@Composable
fun BasicTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    isKeyboardShown: Boolean = false,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    maxLine: Int = 1,
    placeholder: String = "",
    color: Color = text_gray,
    isPrefix: Boolean = false,
    readOnly: Boolean = false,
    isBorderEnable: Boolean = true,
    isVerticalPadding: Boolean = true,
    isIndicatorLine: Boolean = false,
    isLeadingIcon: Boolean = false,
    leadingIcon: ImageVector? = null,
    onValueChanged: ((String) -> Unit)? = null,
    onClick: ((Boolean) -> Unit)? = null,
) {
    val inputValue = rememberSaveable { mutableStateOf("") }

    val addSpace = "          "
    val showKeyboard = remember { mutableStateOf(isKeyboardShown) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    val interactionSource = remember { MutableInteractionSource() }

    val isFocused by interactionSource.collectIsFocusedAsState()

    val indicatorColor = if (isFocused) Purple40 else gray_bg

    if (onClick != null) {
        onClick(isFocused)
    }

    val focusManager = LocalFocusManager.current

    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    // Clearing focus on keyboard hide
    LaunchedEffect(key1 = isKeyboardVisible) {
        if (!isKeyboardVisible) focusManager.clearFocus()
    }

    // selection cursor handle color changing
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Purple40,
        backgroundColor = Purple80,
    )
    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
    ) {
        androidx.compose.foundation.text.BasicTextField(
            value = inputValue.value,
            readOnly = readOnly,
            onValueChange = { newText ->
                if (inputType == KeyboardType.Number) {
                    if (newText.isEmpty()) {
                        inputValue.value = newText
                        if (onValueChanged != null) {
                            onValueChanged(newText)
                        }
                    } else if (!newText.contains(",") && newText.first() != '.') {
                        inputValue.value = newText
                        if (onValueChanged != null) {
                            onValueChanged(newText)
                        }
                    }
                } else {
                    inputValue.value = newText
                    if (onValueChanged != null) {
                        onValueChanged(newText)
                    }
                }

            },

            keyboardOptions = KeyboardOptions(
                imeAction = imeAction,
                keyboardType = inputType
            ),
            cursorBrush = SolidColor(Purple40),
            decorationBox = { innerTextField ->
                if (inputValue.value.isEmpty()) {
                    Row {
                        TextView16_W400(
                            value = if (isPrefix) addSpace + placeholder else placeholder,
                            color = color,
                            modifier = Modifier.weight(4f)
                        )
                        if (isLeadingIcon) {
                            Icon(
                                imageVector = leadingIcon!!, contentDescription = "",
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
                innerTextField()
            },
            singleLine = false,
            maxLines = maxLine,
            interactionSource = interactionSource,
            textStyle = textStyle,
            modifier = modifier
                .border(
                    width = if (isBorderEnable) 1.dp else 0.dp,
                    color = if (isBorderEnable && isFocused) Purple40
                    else if (isBorderEnable && !isFocused) light_gray else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .background(
                    color = if (readOnly) grayRowColor else if (isBorderEnable) Color.White else Color.Unspecified,
                    shape = if (isBorderEnable) RoundedCornerShape(5.dp) else RectangleShape
                )
                .drawCustomIndicatorLine(
                    indicatorBorder = if (isIndicatorLine) BorderStroke(
                        1.dp,
                        indicatorColor
                    ) else BorderStroke(0.dp, indicatorColor),
                    indicatorPadding = 0.dp
                )
                .height(if (isBorderEnable) 42.dp else 30.dp)
                .fillMaxWidth()
                .padding(
                    horizontal = if (isBorderEnable) 12.dp else 0.dp,
                    vertical = if (isBorderEnable) 10.dp else if (isVerticalPadding) 5.dp else 0.dp
                ),
        )
    }

    LaunchedEffect(focusRequester) {
        if (showKeyboard.value) {
            focusRequester.requestFocus()
            delay(100) // Make sure you have delay here
            keyboard?.show()
        }
    }

}

fun Modifier.drawCustomIndicatorLine(
    indicatorBorder: BorderStroke,
    indicatorPadding: Dp = 0.dp
): Modifier {

    val strokeWidthDp = indicatorBorder.width
    return drawWithContent {
        drawContent()
        if (strokeWidthDp == Dp.Hairline) return@drawWithContent
        val strokeWidth = strokeWidthDp.value * density
        val y = size.height - strokeWidth / 2
        drawLine(
            indicatorBorder.brush,
            Offset((indicatorPadding).toPx(), y),
            Offset(size.width - indicatorPadding.toPx(), y),
            strokeWidth
        )
    }
}