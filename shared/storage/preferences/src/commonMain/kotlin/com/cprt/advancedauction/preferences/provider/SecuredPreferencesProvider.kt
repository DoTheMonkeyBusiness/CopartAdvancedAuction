package com.cprt.advancedauction.preferences.provider

import com.cprt.advancedauction.security.encryption.Encryptor

internal class SecuredPreferencesProvider(
    private val preferences: PreferencesProvider.Default,
    private val encryptor: Encryptor.AES
) : PreferencesProvider.Secured {

    override fun getInt(name: String): Int? = getString(name)?.toInt()

    override fun setInt(name: String, value: Int) = setString(name, value.toString())

    override fun getLong(name: String): Long? = getString(name)?.toLong()

    override fun setLong(name: String, value: Long) = setString(name, value.toString())

    override fun getFloat(name: String): Float? = getString(name)?.toFloat()

    override fun setFloat(name: String, value: Float) = setString(name, value.toString())

    override fun getDouble(name: String): Double? = getString(name)?.toDouble()

    override fun setDouble(name: String, value: Double) = setString(name, value.toString())

    override fun getBoolean(name: String): Boolean? = getString(name)?.toBoolean()

    override fun setBoolean(name: String, value: Boolean) = setString(name, value.toString())

    override fun getString(name: String): String? {
        val value = preferences.getString(name)

        return if (value.isNullOrEmpty()) "" else encryptor.decrypt(value)
    }

    override fun setString(name: String, value: String) {
        val encryptedValue = encryptor.encrypt(value)

        preferences.setString(name, encryptedValue)
    }
}
