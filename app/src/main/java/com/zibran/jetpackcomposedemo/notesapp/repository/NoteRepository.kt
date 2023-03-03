package com.zibran.jetpackcomposedemo.notesapp.repository

import android.util.Log
import com.zibran.jetpackcomposedemo.notesapp.data.Notes
import com.zibran.jetpackcomposedemo.notesapp.db.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {


    fun insertNote(notes: Notes): Long {
        return noteDao.insertNote(notes = notes)
    }

    fun getAllNotes(): Flow<List<Notes>> {
        Log.d("MY-TAG", "repository")

        return noteDao.getAllNotes()
    }

    fun getNote(noteId: Int): Flow<Notes> {
        return noteDao.getNote(noteId)
    }

}