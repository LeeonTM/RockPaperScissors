package com.example.rockpaperscissors.database.converters

import androidx.room.TypeConverter
import com.example.rockpaperscissors.enums.Move

class MoveConverter : EnumConverter(){
    @TypeConverter fun moveToTnt(value: Move) = value.toInt()
    @TypeConverter fun intToMove(value: Int) = value.toEnum<Move>()
}