package ru.dezerom.tasks.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.string_container.getString
import ru.dezerom.core.ui.kit.buttons.WhiteButton
import ru.dezerom.core.ui.kit.text_input.MultilineTextInput
import ru.dezerom.core.ui.kit.text_input.TextInput
import ru.dezerom.core.ui.kit.widgets.BottomSheetTopBar
import ru.dezerom.core.ui.kit.widgets.DatePickerWidget
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.tasks.ui.models.TaskEditingState

@Composable
internal fun TaskEditingForm(
    state: TaskEditingState,
    onNewName: (String) -> Unit,
    onNewDescription: (String) -> Unit,
    onNewDeadline: (Long?) -> Unit,
    onButtonClick: () -> Unit,
    title: String,
    buttonText: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.Padding.Medium)
            .padding(bottom = Dimens.Padding.Big)
    ) {
        BottomSheetTopBar(title)
        VSpacer(Dimens.Padding.MediumPlus)
        TextInput(
            value = state.name,
            labelText = stringResource(R.string.task_name),
            onValueChanged = { onNewName(it) },
            testTag = TestTags.TextInput.TASK_NAME,
            modifier = Modifier.fillMaxWidth(),
            isError = state.nameError != null,
            error = state.nameError?.getString()
        )
        VSpacer(Dimens.Padding.Medium)
        MultilineTextInput(
            value = state.description,
            onValueChanged = { onNewDescription(it) },
            labelText = stringResource(R.string.task_description),
            testTag = TestTags.TextInput.TASK_DESCRIPTION,
            modifier = Modifier.fillMaxWidth()
        )
        VSpacer(Dimens.Padding.Medium)
        DatePickerWidget(
            value = state.deadline,
            onValueChanged = onNewDeadline,
            headingText = stringResource(R.string.deadline)
        )
        VSpacer(Dimens.Padding.Big)
        WhiteButton(
            text = buttonText,
            testTag = TestTags.Button.CREATE_TASK_BUTTON,
            onClick = { onButtonClick() },
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
