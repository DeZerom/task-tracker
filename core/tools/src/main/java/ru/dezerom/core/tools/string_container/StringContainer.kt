package ru.dezerom.core.tools.string_container

sealed interface StringContainer {

    class StringRes(val res: Int): StringContainer

    class RawString(val str: String): StringContainer

}
