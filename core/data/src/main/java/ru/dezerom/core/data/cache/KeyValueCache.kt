package ru.dezerom.core.data.cache

interface KeyValueCache {

    suspend fun writeString(key: String, value: String)

    suspend fun readString(key: String): String?

}
