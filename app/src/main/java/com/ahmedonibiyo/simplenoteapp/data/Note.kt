package com.ahmedonibiyo.simplenoteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    @ColumnInfo(name = "title")
    val noteTitle: String,

    @ColumnInfo(name = "description")
    val noteDescription: String,

    @ColumnInfo(name = "timeStamp")
    val timestamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}