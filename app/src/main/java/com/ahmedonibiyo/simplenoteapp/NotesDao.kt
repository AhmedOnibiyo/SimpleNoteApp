package com.ahmedonibiyo.simplenoteapp

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface NotesDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}