package com.cprt.advancedauction.preferences.provider

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

internal actual open class DefaultPreferencesProvider(
    name: String,
    context: Context,
) : PreferencesProvider.Default {

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

    actual override fun getInt(name: String): Int? = get(intPreferencesKey(name))
    actual override fun setInt(name: String, value: Int) = set(intPreferencesKey(name), value)

    actual override fun getLong(name: String): Long? = get(longPreferencesKey(name))
    actual override fun setLong(name: String, value: Long) = set(longPreferencesKey(name), value)

    actual override fun getFloat(name: String): Float? = get(floatPreferencesKey(name))
    actual override fun setFloat(name: String, value: Float) = set(floatPreferencesKey(name), value)

    actual override fun getDouble(name: String): Double? = get(doublePreferencesKey(name))
    actual override fun setDouble(name: String, value: Double) = set(doublePreferencesKey(name), value)

    actual override fun getBoolean(name: String): Boolean? = get(booleanPreferencesKey(name))
    actual override fun setBoolean(name: String, value: Boolean) = set(booleanPreferencesKey(name), value)

    actual override fun getString(name: String): String? = get(stringPreferencesKey(name))
    actual override fun setString(name: String, value: String) = set(stringPreferencesKey(name), value)
}
