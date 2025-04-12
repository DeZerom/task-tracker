package ru.dezerom.tasks.data.mappers

import ru.dezerom.tasks.data.models.TaskDataModel
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

fun TasksListNetworkDto.toDomainList(): List<TaskDataModel> {
    return tasks.map { it.toDataModel() }
}