package com.cprt.advancedauction.preferences.provider

internal expect class DefaultPreferencesProvider : PreferencesProvider.Default {
    override fun getInt(name: String): Int?
    override fun setInt(name: String, value: Int)

    override fun getLong(name: String): Long?
    override fun setLong(name: String, value: Long)

    override fun getFloat(name: String): Float?
    override fun setFloat(name: String, value: Float)

    override fun getDouble(name: String): Double?
    override fun setDouble(name: String, value: Double)

    override fun getBoolean(name: String): Boolean?
    override fun setBoolean(name: String, value: Boolean)

    override fun getString(name: String): String?
    override fun setString(name: String, value: String)
}
