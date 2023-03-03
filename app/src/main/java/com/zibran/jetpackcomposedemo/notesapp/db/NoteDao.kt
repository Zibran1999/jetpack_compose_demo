package com.zibran.jetpackcomposedemo.notesapp.db

import androidx.room.*
import com.zibran.jetpackcomposedemo.notesapp.data.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    fun insertNote(notes: Notes): Long

    @Update
    fun updateNote(notes: Notes): Int

    @Delete
    fun deleteNote(notes: Notes): Int

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE note_id In (:noteId)")
    fun getNote(noteId: Int): Flow<Notes>

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()

}