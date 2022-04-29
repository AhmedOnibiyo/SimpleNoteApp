package com.ahmedonibiyo.simplenoteapp.data

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

    @Query("SELECT * FROM notesTable ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notesTable WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Note>>
}