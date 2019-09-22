package com.example.rockpaperscissors.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rockpaperscissors.database.converters.DateConverter
import com.example.rockpaperscissors.database.converters.MoveConverter
import com.example.rockpaperscissors.database.converters.ResultConverter
import com.example.rockpaperscissors.enums.Move
import com.example.rockpaperscissors.enums.Result
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "game")
data class Game (
    @ColumnInfo(name = "date")
    var date: Date?,

    @ColumnInfo(name = "computerMove")
    var computerMove: Move,

    @ColumnInfo(name = "playerMove")
    var playerMove: Move,

    @ColumnInfo(name = "result")
    var result: Result? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable