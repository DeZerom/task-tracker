package ru.dezerom.tasks.data.mappers

import ru.dezerom.tasks.data.models.TaskModel
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

fun TasksListNetworkDto.toDomainList(): List<TaskModel> {
    return tasks.map { it.toDomain() }
}