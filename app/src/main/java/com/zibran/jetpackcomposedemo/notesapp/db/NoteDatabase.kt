package com.zibran.jetpackcomposedemo.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zibran.jetpackcomposedemo.notesapp.data.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object {
        var DB_INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {

            if (DB_INSTANCE == null) {

                DB_INSTANCE = Room.databaseBuilder(
                    context, NoteDatabase::class.java, "notes_db"
                )
                    .build()
                return DB_INSTANCE!!
            }
            return DB_INSTANCE!!

        }

    }
}


