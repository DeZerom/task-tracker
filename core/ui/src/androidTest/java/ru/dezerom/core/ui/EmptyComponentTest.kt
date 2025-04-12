package ru.dezerom.core.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import ru.dezerom.core.ui.kit.widgets.EmptyListComponent
import ru.dezerom.core.ui.test_tools.TestTags

class EmptyComponentTest {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun emptyComponentTest_components() {
        testRule.setContent {
            EmptyListComponent("empty")
        }

        testRule.onNodeWithTag(TestTags.Image.WARNING_ICON).assertIsDisplayed()

        testRule.onNodeWithText("empty").assertIsDisplayed()
    }
}
