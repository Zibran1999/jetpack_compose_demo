package com.zibran.jetpackcomposedemo.notesapp.model

import android.util.Log
import androidx.lifecycle.*
import com.zibran.jetpackcomposedemo.notesapp.data.Notes
import com.zibran.jetpackcomposedemo.notesapp.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private var noteMutableData = MutableLiveData<List<Notes>>()
    val noteLiveData: LiveData<List<Notes>>
        get() = noteMutableData

    fun insertNote(notes: Notes): Long {
        return repository.insertNote(notes)
    }

    fun getAllNotes() {
        Log.d("MY-TAG", "it.noteTitle")

        viewModelScope.launch {
            repository.getAllNotes().collect {
                noteMutableData.value = it
            }
        }


    }


    fun getNote(noteId: Int) = liveData {
        repository.getNote(noteId = noteId).collect {
            emit(it)
        }


    }


}