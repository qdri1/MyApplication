package com.example.myapplication

import android.content.Context
import java.lang.Exception
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
fun <T> T.getValueOf(propertyName: String): ContextWrapper where T : Any, T : ModelExtension {
    val kClass: KClass<T> = javaClass.kotlin
    val property = kClass.memberProperties.firstOrNull { it.name == propertyName } as? KProperty1<Any, *>
        ?: throw IllegalArgumentException("Undefined property")
    val propertyValue = property.get(this).toString()
    val stringKey = kClass.getStringResourceKey(propertyName)
    return ContextWrapper(stringKey, propertyValue)
}

class ContextWrapper(private val stringKey: String, private val propertyValue: String) {

    infix fun from(context: Context): String {
        return try {
            val res = context.resources.getIdentifier(stringKey, "string", context.packageName)
            context.getString(res, propertyValue)
        } catch (e: Exception) {
            propertyValue
        }
    }

}

@Suppress("UNCHECKED_CAST")
private fun <T> KClass<T>.getStringResourceKey(propertyName: String): String where T : ModelExtension {
    val property = memberProperties.first { it.name == propertyName } as KProperty1<Any, *>
    val modelClassName = simpleName?.toLowerCase(Locale.getDefault())
    val modelPropertyName = property.name
    return "${modelClassName}_${modelPropertyName}"
}