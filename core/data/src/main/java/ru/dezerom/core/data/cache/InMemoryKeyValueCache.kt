package ru.dezerom.core.data.cache

class InMemoryKeyValueCache: KeyValueCache {
    private val storedData = mutableMapOf<String, Any>()

    override suspend fun writeString(key: String, value: String) {
        storedData[key] = value
    }

    override suspend fun readString(key: String): String? {
        val data = storedData[key]

        return if (data is String) data else null
    }

}
