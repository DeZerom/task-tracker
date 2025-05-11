package ru.dezerom.tasks.ui.list

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.core.tools.consts.Dimens
import ru.dezerom.core.tools.extensions.toYearMonthDay
import ru.dezerom.core.ui.kit.text_style.TS
import ru.dezerom.core.ui.kit.theme.TaskTrackerTheme
import ru.dezerom.core.ui.kit.widgets.HSpacer
import ru.dezerom.core.ui.kit.widgets.VSpacer
import ru.dezerom.core.ui.test_tools.TestTags
import ru.dezerom.tasks.domain.models.TaskModel

@Composable
internal fun TaskComponent(
    task: TaskModel,
    onCompleted: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    var isLongName by remember { mutableStateOf(false) }
    var isLongDescription by remember { mutableStateOf(false) }
    val isLong = remember(isLongName, isLongDescription) {
        derivedStateOf { isLongName || isLongDescription }
    }

    Card(
        shape = RoundedCornerShape(Dimens.CornerRadius.Default),
        colors = CardDefaults.cardColors(containerColor = Colors.darkSurface),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(Dimens.Padding.Small)
        ) {
            Row {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onCompleted,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Colors.accent,
                        uncheckedColor = Colors.accent,
                        checkmarkColor = Colors.white,
                    ),
                    modifier = Modifier
                        .size(24.dp)
                        .testTag(TestTags.Objects.CHECK_BOX)
                )
                HSpacer(Dimens.Padding.Small)
                Text(
                    text = task.name,
                    style = TS.titleLarge,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { isLongName = isLongName(it) },
                    modifier = Modifier.animateContentSize()
                )
            }
            if (task.description.isNotBlank()) {
                VSpacer(Dimens.Padding.Small)
                Text(
                    text = task.description,
                    style = TS.bodyMedium.copy(color = Colors.secondaryText),
                    maxLines = if (expanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { isLongDescription = isLongDescription(it) },
                    modifier = Modifier.animateContentSize()
                )
            }
            if (isLong.value) {
                VSpacer(Dimens.Padding.Small)
                ExpandComponent(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            VSpacer(Dimens.Padding.Medium)
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier.fillMaxWidth()
             ) {
                 Text(
                     text = stringResource(R.string.created_at, task.createdAt.toYearMonthDay()),
                     style = TS.bodyMedium,
                     modifier = Modifier.weight(1f)
                 )
                 if (task.deadline != null) {
                     Text(
                         text = stringResource(
                             R.string.deadline_at, task.deadline!!.toYearMonthDay()
                         ),
                         style = TS.bodyMedium,
                     )
                 }
             }
            if (task.isCompleted && task.completedAt != null) {
                Text(
                    text = stringResource(R.string.completed_at, task.completedAt!!.toYearMonthDay()),
                    style = TS.bodyMedium.copy(color = Colors.secondaryText)
                )
            }
        }
    }
}

@Composable
private fun ExpandComponent(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotate by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Card(
        shape = RoundedCornerShape(Dimens.CornerRadius.Default),
        colors = CardDefaults.cardColors(containerColor = Colors.background),
        onClick = onClick,
        modifier = modifier.testTag(TestTags.Components.EXPAND_COMPONENT)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(Dimens.Padding.VerySmall)
                .padding(start = Dimens.Padding.VerySmall)
        ) {
            Text(
                text = stringResource(
                    if (expanded) R.string.collapse
                    else R.string.expand
                ),
                style = TS.bodySmall.copy(color = Colors.white),
                modifier = Modifier.weight(1f),
            )
            HSpacer(Dimens.Padding.Small)
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(R.string.expand),
                tint = Colors.white,
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

private fun isLongDescription(textResult: TextLayoutResult): Boolean {
    return textResult.lineCount > 3
            || textResult.isLineEllipsized(2.coerceAtMost(textResult.lineCount - 1))
}

private fun isLongName(textResult: TextLayoutResult): Boolean {
    return textResult.lineCount > 2
            || textResult.isLineEllipsized(1.coerceAtMost(textResult.lineCount - 1))
}

@Preview
@Composable
private fun TaskComponentPreview() {
    TaskTrackerTheme {
        TaskComponent(
            task = TaskModel(
                id = "123",
                name = "Long long long long task name Long long long long task name Long long long long task name Long long long long task name Long long long long task name Long long long long task name Long long long long task name Long long long long task name",
                description = "aLong long long long task name Long long long long task name Long long long long task name Long long long long task namesd aLong long long long task name Long long long long task name Long long long long task name Long long long long task namesd aLong long long long task name Long long long long task name Long long long long task name Long long long long task namesd",
                createdAt = 1000,
                isCompleted = true,
                deadline = 1000000000000,
                completedAt = 100000000,
            ),
//            task = TaskModel(
//                id = "123",
//                name = "qwe",
//                description = "asd",
//                createdAt = 1000,
//                isCompleted = true,
//                deadline = 1000000000000,
//                completedAt = 100000000,
//            ),
            onCompleted = {}
        )
    }
}
