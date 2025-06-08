package ru.dezerom.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.widgets.AffectScaffold
import ru.dezerom.core.ui.kit.widgets.HSpacer
import ru.dezerom.core.ui.kit.widgets.TopLevelTopBar
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.tools.PreviewWrapper
import ru.dezerom.profile.ui.ProfileScreenContract.Event

@Composable
fun ProfileScreen() {
    ProfileContent(
        onEvent = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileContent(
    onEvent: (Event) -> Unit
) {
    AffectScaffold(
        topBar = {
            TopLevelTopBar(
                title = stringResource(R.string.profile)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Padding.Medium)
        ) {
            VSpacer(Dimens.Padding.Medium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.Sizes.IconBig)
                )
                Text(
                    text = "qwe",
                    style = TS.titleLarge
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onEvent(Event.Logout) },
                colors = ButtonDefaults.buttonColors().copy(containerColor = Colors.error),
                shape = RoundedCornerShape(Dimens.CornerRadius.Default),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ExitToApp,
                    contentDescription = null,
                    tint = Colors.white
                )
                HSpacer(Dimens.Padding.Small)
                Text(
                    text = stringResource(R.string.logout),
                    style = TS.bodyMedium.copy(color = Colors.white)
                )
            }
        }
    }
}

@Composable
@Preview
private fun ProfilePreview() {
    PreviewWrapper {
        ProfileContent(
            onEvent = {}
        )
    }
}