package com.cprt.advancedauction.preferences.provider

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal expect open class PreferencesProvider(name: String) {
    open fun getInt(name: String): Int?
    open fun setInt(name: String, value: Int)

    open fun getLong(name: String): Long?
    open fun setLong(name: String, value: Long)

    open fun getFloat(name: String): Float?
    open fun setFloat(name: String, value: Float)

    open fun getDouble(name: String): Double?
    open fun setDouble(name: String, value: Double)

    open fun getBoolean(name: String): Boolean?
    open fun setBoolean(name: String, value: Boolean)

    open fun getString(name: String): String?
    open fun setString(name: String, value: String)
}

internal fun PreferencesProvider.intProperty(default: Int) = Property(default, ::getInt, ::setInt)

internal fun PreferencesProvider.longProperty(default: Long) = Property(default, ::getLong, ::setLong)

internal fun PreferencesProvider.floatProperty(default: Float) = Property(default, ::getFloat, ::setFloat)

internal fun PreferencesProvider.doubleProperty(default: Double) = Property(default, ::getDouble, ::setDouble)

internal fun PreferencesProvider.booleanProperty(default: Boolean) = Property(default, ::getBoolean, ::setBoolean)

internal fun PreferencesProvider.stringProperty(default: String) = Property(default, ::getString, ::setString)

internal class Property<Type> constructor(
    private val default: Type,
    private val reader: (name: String) -> Type?,
    private val writer: (name: String, value: Type) -> Unit
) : ReadWriteProperty<Any?, Type> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Type = reader(property.name) ?: default
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Type) = writer(property.name, value)
}
