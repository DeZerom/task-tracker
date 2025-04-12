package ru.dezerom.core.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import ru.dezerom.core.tools.R
import ru.dezerom.core.ui.kit.widgets.DefaultLoaderComponent
import ru.dezerom.core.ui.test_tools.TestTags

class LoaderComponentTest {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun tasksListLoading_components() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        testRule.setContent {
            DefaultLoaderComponent()
        }

        testRule.onNodeWithTag(TestTags.Objects.LOADER).assertIsDisplayed()

        testRule.onNodeWithText(context.getString(R.string.loading)).assertIsDisplayed()
    }
}