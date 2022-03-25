package com.cprt.advancedauction.preferences.util

import com.cprt.advancedauction.preferences.provider.PreferencesProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
