package com.cprt.advancedauction.preferences.provider

import java.util.prefs.Preferences

internal actual class DefaultPreferencesProvider(
    name: String,
) : PreferencesProvider.Default {
    private val store = Preferences.userRoot().node(name)

    actual override fun getInt(name: String): Int? = store.getInt(name, 0)
    actual override fun setInt(name: String, value: Int) = store.putInt(name, value)

    actual override fun getLong(name: String): Long? = store.getLong(name, 0)
    actual override fun setLong(name: String, value: Long) = store.putLong(name, value)

    actual override fun getFloat(name: String): Float? = store.getFloat(name, 0F)
    actual override fun setFloat(name: String, value: Float) = store.putFloat(name, value)

    actual override fun getDouble(name: String): Double? = store.getDouble(name, 0.0)
    actual override fun setDouble(name: String, value: Double) = store.putDouble(name, value)

    actual override fun getBoolean(name: String): Boolean? = store.getBoolean(name, false)
    actual override fun setBoolean(name: String, value: Boolean) = store.putBoolean(name, value)

    actual override fun getString(name: String): String? = store.get(name, "")
    actual override fun setString(name: String, value: String) = store.put(name, value)
}
