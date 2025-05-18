package ru.dezerom.tasks.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.text_input.MultilineTextInput
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.widgets.BottomSheetTopBar
import ru.dezerom.core.ui.kit.widgets.DatePickerWidget
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.tasks.ui.models.TaskUiModel

@Composable
internal fun TaskEditingForm(
    task: TaskUiModel,
    onNewName: (String) -> Unit,
    onNewDescription: (String) -> Unit,
    onNewDeadline: (Long?) -> Unit,
    onCreate: () -> Unit,
    isLoading: Boolean,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.Padding.Medium)
            .padding(bottom = Dimens.Padding.Big)
    ) {
        BottomSheetTopBar(stringResource(R.string.task))
        VSpacer(Dimens.Padding.MediumPlus)
        TextInput(
            value = task.name,
            labelText = stringResource(R.string.task_name),
            onValueChanged = { onNewName(it) },
            testTag = TestTags.TextInput.TASK_NAME,
            modifier = Modifier.fillMaxWidth()
        )
        VSpacer(Dimens.Padding.Medium)
        MultilineTextInput(
            value = task.description,
            onValueChanged = { onNewDescription(it) },
            labelText = stringResource(R.string.task_description),
            testTag = TestTags.TextInput.TASK_DESCRIPTION,
            modifier = Modifier.fillMaxWidth()
        )
        VSpacer(Dimens.Padding.Medium)
        DatePickerWidget(
            value = task.deadline,
            onValueChanged = onNewDeadline,
            headingText = stringResource(R.string.deadline)
        )
        VSpacer(Dimens.Padding.Big)
        WhiteButton(
            text = stringResource(R.string.add_task),
            testTag = TestTags.Button.CREATE_TASK_BUTTON,
            onClick = { onCreate() },
            isLoading = isLoading,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
