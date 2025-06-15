package ru.dezerom.core.tools.errors

import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.StringContainer
import java.io.IOException

class NetworkError(val messageRes: StringContainer): IOException()

fun unknownNetworkError() = NetworkError(StringContainer.StringRes(R.string.unknown_error))

fun unAuthorizedNetworkError() = NetworkError(StringContainer.StringRes(R.string.authorize))
