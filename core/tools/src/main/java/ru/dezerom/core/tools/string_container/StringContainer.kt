package ru.dezerom.core.tools.string_container

import androidx.compose.runtime.Immutable

@Immutable
sealed interface StringContainer {

    @Immutable
    class StringRes(val res: Int): StringContainer

    @Immutable
    class RawString(val str: String): StringContainer

}
