package ru.dezerom.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.widgets.AffectScaffold
import ru.dezerom.core.ui.kit.widgets.Loader
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.tools.PreviewWrapper
import ru.dezerom.core.ui.tools.ProcessSideEffects
import ru.dezerom.navigation.api.navigators.SplashNavigator

@Composable
fun SplashScreen(
    navigator: SplashNavigator
) {
    val viewModel: SplashScreenViewModel = hiltViewModel()

    ProcessSideEffects(viewModel.sideEffect) {
        when (it) {
            SplashScreenContract.SideEffect.GoToAuth -> navigator.fromSplashToAuth()
            SplashScreenContract.SideEffect.GoToTasks -> navigator.fromSplashToTasks()
        }
    }

    SplashScreenContent()
}

@Composable
private fun SplashScreenContent() {
    AffectScaffold(
        showBottomNavBar = false
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(Dimens.Sizes.IconExtraBig)
            )
            VSpacer(Dimens.Padding.Big)
            Loader()
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    PreviewWrapper {
        SplashScreenContent()
    }
}