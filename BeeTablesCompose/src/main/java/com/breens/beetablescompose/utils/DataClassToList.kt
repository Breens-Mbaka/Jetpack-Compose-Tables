package com.breens.beetablescompose.utils

import kotlin.reflect.KProperty1
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any> extractMembers(instance: T): List<Pair<String, String>> {
    val members = mutableListOf<Pair<String, String>>()

    // Get the primary constructor of the data class
    val constructor = T::class.primaryConstructor

    // Get the parameter names in the constructor declaration order
    val parameterNames = constructor?.parameters?.map { it.name } ?: emptyList()

    // Get all properties of the class
    val properties = T::class.members.filterIsInstance<KProperty1<T, *>>()

    // Filter and sort properties based on constructor parameter order
    val sortedProperties = properties.filter { it.name in parameterNames }
        .sortedBy { parameterNames.indexOf(it.name) }

    sortedProperties.forEach { member ->
        val name = member.name
        val value = member.get(instance)?.toString() ?: "null"
        members.add(name to value)
    }

    return members
}
