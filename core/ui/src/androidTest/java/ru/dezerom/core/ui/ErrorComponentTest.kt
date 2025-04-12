package ru.dezerom.core.ui

import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.toStringContainer
import ru.dezerom.core.ui.kit.widgets.DefaultErrorComponent
import ru.dezerom.core.ui.test_tools.TestTags

@RunWith(AndroidJUnit4::class)
class ErrorComponentTest {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun tasksListError_components() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            DefaultErrorComponent(
                err = "Err".toStringContainer(),
                onTryAgain = {}
            )
        }

        testRule.onNodeWithTag(TestTags.Image.WARNING_ICON)
            .assertIsDisplayed()
            .assertHeightIsEqualTo(Dimens.Sizes.IconExtraBig)
            .assertWidthIsEqualTo(Dimens.Sizes.IconExtraBig)


        testRule.onNodeWithText(context.getString(R.string.error_while_loading))
            .assertIsDisplayed()

        testRule.onNodeWithText("Err").assertIsDisplayed()

        testRule.onNodeWithTag(TestTags.Button.TRY_AGAIN_BUTTON)
            .assertIsDisplayed()
            .assertTextEquals(context.getString(R.string.try_again))
    }
}