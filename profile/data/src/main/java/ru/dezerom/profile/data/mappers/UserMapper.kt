package ru.dezerom.profile.data.mappers

import ru.dezerom.profile.data.models.UserDataModel
import ru.dezerom.profile.data.network.models.UserNetworkDto
import ru.dezerom.profile.domain.models.UserModel

fun UserNetworkDto.toDataModel() = UserDataModel(
    id = id,
    login = login,
    tasks = tasks,
    completedTasks = completedTasks,
    uncompletedTasks = uncompletedTasks
)

fun UserDataModel.toDomain() = UserModel(
    id = id ?: "",
    login = login ?: "",
    tasks = tasks ?: 0,
    completedTasks = completedTasks ?: 0,
    uncompletedTasks = uncompletedTasks ?: 0
)