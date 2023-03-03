package com.zibran.jetpackcomposedemo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zibran.jetpackcomposedemo.notesapp.data.Notes
import com.zibran.jetpackcomposedemo.notesapp.db.NoteDao
import com.zibran.jetpackcomposedemo.notesapp.db.NoteDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var noteDao: NoteDao
    lateinit var noteDatabase: NoteDatabase

    @Before
    fun setUp() {

        noteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()

        noteDao = noteDatabase.noteDao()
    }

    @Test

    fun insertData() {

      val result=  noteDao.insertNote(Notes(0, "New Data", "New data for test")).toInt()


//        aseertThat().

    }
}