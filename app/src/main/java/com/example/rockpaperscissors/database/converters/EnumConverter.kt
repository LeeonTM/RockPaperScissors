package com.example.rockpaperscissors.database.converters

abstract class EnumConverter {
    @Suppress("NOTHING_TO_INLINE")
    inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal
    inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]
}