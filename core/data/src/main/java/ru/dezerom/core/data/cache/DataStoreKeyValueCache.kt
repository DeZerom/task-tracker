package ru.dezerom.core.data.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreKeyValueCache(
    private val store: DataStore<Preferences>
): KeyValueCache {

    override suspend fun writeString(key: String, value: String) {
        store.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun readString(key: String): String? {
        return store.data.map { it[stringPreferencesKey(key)] }.firstOrNull()
    }

}
