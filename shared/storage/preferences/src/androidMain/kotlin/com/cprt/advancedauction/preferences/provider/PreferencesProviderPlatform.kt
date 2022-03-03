package com.cprt.advancedauction.preferences.provider

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal actual open class PreferencesProvider
actual constructor(name: String) : KoinComponent {

    private val context: Context by inject()

    private val store = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(name)
    }

    private fun <T> get(key: Preferences.Key<T>): T? = runBlocking {
        store.data.first()[key]
    }

    private fun <T> set(key: Preferences.Key<T>, value: T?) = runBlocking<Unit> {
        store.edit {
            if (value == null) {
                it.remove(key)
            } else {
                it[key] = value
            }
        }
    }

    actual open fun getInt(name: String): Int? = get(intPreferencesKey(name))
    actual open fun setInt(name: String, value: Int) = set(intPreferencesKey(name), value)

    actual open fun getLong(name: String): Long? = get(longPreferencesKey(name))
    actual open fun setLong(name: String, value: Long) = set(longPreferencesKey(name), value)

    actual open fun getFloat(name: String): Float? = get(floatPreferencesKey(name))
    actual open fun setFloat(name: String, value: Float) = set(floatPreferencesKey(name), value)

    actual open fun getDouble(name: String): Double? = get(doublePreferencesKey(name))
    actual open fun setDouble(name: String, value: Double) = set(doublePreferencesKey(name), value)

    actual open fun getBoolean(name: String): Boolean? = get(booleanPreferencesKey(name))
    actual open fun setBoolean(name: String, value: Boolean) = set(booleanPreferencesKey(name), value)

    actual open fun getString(name: String): String? = get(stringPreferencesKey(name))
    actual open fun setString(name: String, value: String) = set(stringPreferencesKey(name), value)
}
