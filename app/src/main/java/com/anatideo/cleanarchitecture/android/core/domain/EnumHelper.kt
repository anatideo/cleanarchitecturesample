package com.anatideo.cleanarchitecture.android.core.domain

object EnumHelper {
    /**
     * Returns an enum entry with the specified name or `null` if no such entry was found.
     * Usage: enumValueOfOrNull<MyEnum>("some string")
     */
    inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String?): T? {
        return enumValues<T>().find { it.name == name }
    }

    /**
     * Returns an enum entry with the specified name or `default` if no such entry was found.
     * Usage: enumValueOfOrDefault<MyEnum>("some string", "some enum value")
     */
    inline fun <reified T : Enum<T>> enumValueOfOrDefault(name: String?, default: T): T {
        return enumValues<T>().find { it.name == name } ?: default
    }

    /**
     * Returns `true` if enum T contains an entry with the specified name.
     * Usage: enumContains<MyEnum>("some string")
     */
    inline fun <reified T : Enum<T>> enumContains(name: String?): Boolean {
        return T::class.java.enumConstants?.any { it.name == name } == true
    }
}