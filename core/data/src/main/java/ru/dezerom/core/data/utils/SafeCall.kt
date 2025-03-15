package ru.dezerom.core.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.errors.NetworkError
import ru.dezerom.core.tools.errors.unknownNetworkError
import ru.dezerom.core.tools.string_container.toStringContainer

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> ResponseDto<T?>
): Result<T> = withContext(dispatcher) {
    try {
        val result = call()
        if (result.success) {
            if (result.body != null)
                Result.success(result.body)
            else
                Result.failure(NetworkError(R.string.unknown_error.toStringContainer()))
        } else {
            val err = result.error
            val errorMessage = err?.toStringContainer()
                ?: R.string.unknown_error.toStringContainer()

            Result.failure(NetworkError(errorMessage))
        }
    } catch (e: Exception) {
        Result.failure(unknownNetworkError())
    }
}
