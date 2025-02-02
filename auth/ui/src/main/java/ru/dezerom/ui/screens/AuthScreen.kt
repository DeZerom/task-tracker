package ru.dezerom.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.navigation.api.destinations.RegistrationDestination
import ru.dezerom.navigation.api.tools.LocalNavController

@Composable
fun AuthScreen() {
    val navController = LocalNavController.current

    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(all = Dimens.Padding.Medium)
        ) {
            VSpacer(height = Dimens.Padding.XXXBig)
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = null,
            )
            VSpacer(height = Dimens.Padding.MediumPlus)
            Text(
                stringResource(id = R.string.authorization),
                style = TS.titleLarge
            )
            VSpacer(height = Dimens.Padding.Big)
            TextInput(
                value = "",
                labelText = stringResource(id = R.string.login),
                onValueChanged = {},
                modifier = Modifier.fillMaxWidth()
            )
            VSpacer(height = Dimens.Padding.Medium)
            TextInput(
                value = "",
                labelText = stringResource(id = R.string.password),
                onValueChanged = {},
                modifier = Modifier.fillMaxWidth()
            )
            VSpacer(height = Dimens.Padding.Small)
            Text(
                text = stringResource(id = R.string.create_account),
                color = Colors.secondaryText,
                style = TS.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .clickable { navController.navigate(RegistrationDestination) }
            )
            Spacer(modifier = Modifier.weight(1f))
            WhiteButton(
                onClick = {},
                text = stringResource(id = R.string.authorize),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
