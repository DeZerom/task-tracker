package ru.dezerom.core.ui.kit.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS

@Composable
fun BaseButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Colors.white
) {
    Button(
        onClick = onClick,
        content = {
            Text(
                text = text,
                style = TS.bodyLarge,
                color = textColor
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(Dimens.CornerRadius.Default),
        modifier = Modifier
            .height(Dimens.Sizes.ButtonHeight)
            .then(modifier)
    )
}
