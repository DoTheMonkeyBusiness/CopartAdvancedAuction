package com.cprt.advancedauction.preferences.provider

import java.util.prefs.Preferences

internal actual open class PreferencesProvider actual constructor(name: String) {

    private val store = Preferences.userRoot().node(name)

    actual open fun getInt(name: String): Int? = store.getInt(name, 0)
    actual open fun setInt(name: String, value: Int) = store.putInt(name, value)

    actual open fun getLong(name: String): Long? = store.getLong(name, 0)
    actual open fun setLong(name: String, value: Long) = store.putLong(name, value)

    actual open fun getFloat(name: String): Float? = store.getFloat(name, 0F)
    actual open fun setFloat(name: String, value: Float) = store.putFloat(name, value)

    actual open fun getDouble(name: String): Double? = store.getDouble(name, 0.0)
    actual open fun setDouble(name: String, value: Double) = store.putDouble(name, value)

    actual open fun getBoolean(name: String): Boolean? = store.getBoolean(name, false)
    actual open fun setBoolean(name: String, value: Boolean) = store.putBoolean(name, value)

    actual open fun getString(name: String): String? = store.get(name, "")
    actual open fun setString(name: String, value: String) = store.put(name, value)
}
