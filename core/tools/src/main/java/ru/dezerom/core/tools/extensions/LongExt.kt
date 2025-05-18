package ru.dezerom.core.tools.extensions

import android.text.format.DateFormat
import java.util.Date

fun Long.toYearMonthDay(): String {
    val date = Date(this)
    return DateFormat.format("dd.MM.yyyy", date).toString()
}
