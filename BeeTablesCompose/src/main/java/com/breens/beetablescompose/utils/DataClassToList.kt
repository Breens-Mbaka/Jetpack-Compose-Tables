/*
 * Copyright 2023 Breens Mbaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
