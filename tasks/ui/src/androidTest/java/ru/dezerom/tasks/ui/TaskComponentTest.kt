package ru.dezerom.tasks.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.tasks.domain.models.TaskModel
import ru.dezerom.tasks.ui.list.TaskComponent

class TaskComponentTest {

    @get:Rule val testRule = createComposeRule()

    @Test
    fun taskComponent_checkChangesOnComplete() {
        testRule.setContent {
            var task by remember {
                mutableStateOf(
                    TaskModel(
                        id = "",
                        name = "12",
                        description = "123",
                        deadline = null,
                        createdAt = 1,
                        isCompleted = false,
                        completedAt = null,
                    )
                )
            }

            TaskComponent(
                task = task,
                onChangeCompleteStatus = { task = task.copy(isCompleted = !task.isCompleted) }
            )
        }

        testRule.onNodeWithTag(TestTags.Objects.CHECK_BOX)
            .assertIsDisplayed()
            .assertIsOff()
            .performClick()
            .assertIsDisplayed()
            .assertIsOn()
            .performClick()
            .assertIsDisplayed()
            .assertIsOff()
    }

    @Test
    fun taskComponent_expandButtonDoesNotDisappear() {
        testRule.setContent {
            TaskComponent(
                task = TaskModel(
                    id = "",
                    name = "12345\n12345\n12345",
                    description = "123",
                    deadline = null,
                    createdAt = 1,
                    isCompleted = false,
                    completedAt = null,
                ),
                onChangeCompleteStatus = {}
            )
        }

        testRule.onNodeWithTag(TestTags.Components.EXPAND_COMPONENT)
            .assertIsDisplayed()
            .performClick()
            .assertIsDisplayed()
            .performClick()
            .assertIsDisplayed()
    }
}
