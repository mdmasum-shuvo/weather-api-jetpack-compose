package com.masum.weather.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.masum.weather.ui.theme.gray_bg
import com.masum.weather.ui.theme.text_gray
import com.masum.weather.ui.theme.white_color


@Composable
fun TextFieldWithBorder(
    modifier: Modifier = Modifier,
    startIcon: Int? = null,
    endIcon: Int? = null,
    isKeyboardShown: Boolean = false,
    inputType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    maxLine: Int = 1,
    placeholder: String = "",
    color: Color = text_gray,
    isPrefix: Boolean = false,
    readOnly: Boolean = false,
    isIndicatorLine: Boolean = false,
    isLeadingIcon: Boolean = false,
    leadingIcon: ImageVector? = null,
    onIconClick: () -> Unit = {},
    onValueChanged: ((String) -> Unit)? = null,
) {
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Card(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(width = 1.dp, color = if (isKeyboardVisible) white_color else gray_bg)
    ) {
        Row(
            modifier = Modifier
                .background(white_color)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (startIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onIconClick() },
                    painter = painterResource(id = startIcon),
                    tint = Color.Unspecified, // Icon Color
                    contentDescription = "Start Icon"
                )
                Spacer8DPW()
            }
            BasicTextField(
                isKeyboardShown = isKeyboardShown,
                modifier = modifier.weight(1f),
                inputType = inputType,
                imeAction = imeAction,
                maxLine = maxLine,
                placeholder = placeholder,
                color = color,
                isPrefix = isPrefix,
                readOnly = readOnly,
                isBorderEnable = false,
                isIndicatorLine = isIndicatorLine,
                isLeadingIcon = isLeadingIcon,
                leadingIcon = leadingIcon, onValueChanged = { value ->
                    onValueChanged!!(value)
                }
            )
            if (endIcon != null) {
                Spacer8DPW()
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onIconClick() },
                    painter = painterResource(id = endIcon),
                    tint = Color.Unspecified, // Icon Color
                    contentDescription = "End Icon"
                )
            }
        }
    }
}