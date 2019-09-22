package com.example.rockpaperscissors.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rockpaperscissors.enums.Move
import com.example.rockpaperscissors.enums.Result

class ResultConverter : EnumConverter() {
    @TypeConverter fun resultToTnt(value: Result) = value.toInt()
    @TypeConverter fun intToResult(value: Int) = value.toEnum<Result>()
}