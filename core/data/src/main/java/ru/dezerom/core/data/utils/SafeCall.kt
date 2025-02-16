package ru.dezerom.core.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.errors.unknownNetworkError
import ru.dezerom.core.tools.string_container.StringContainer

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> ResponseDto<T>
): Result<T> = withContext(dispatcher) {
    try {
        val result = call()
        if (result.success) {
            Result.success(result.body)
        } else {
            Result.failure(NetworkError(StringContainer.RawString(result.error)))
        }
    } catch (e: Exception) {
        Result.failure(unknownNetworkError())
    }
}
