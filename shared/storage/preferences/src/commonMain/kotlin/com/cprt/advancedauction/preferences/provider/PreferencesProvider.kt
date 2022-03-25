package com.cprt.advancedauction.preferences.provider

internal sealed interface PreferencesProvider {

    interface Default : PreferencesProvider

    interface Secured : PreferencesProvider

    fun getInt(name: String): Int?
    fun setInt(name: String, value: Int)

    fun getLong(name: String): Long?
    fun setLong(name: String, value: Long)

    fun getFloat(name: String): Float?
    fun setFloat(name: String, value: Float)

    fun getDouble(name: String): Double?
    fun setDouble(name: String, value: Double)

    fun getBoolean(name: String): Boolean?
    fun setBoolean(name: String, value: Boolean)

    fun getString(name: String): String?
    fun setString(name: String, value: String)
}
