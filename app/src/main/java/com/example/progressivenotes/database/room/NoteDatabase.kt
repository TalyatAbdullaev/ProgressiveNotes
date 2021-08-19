package com.example.progressivenotes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        var INSTANCE: NoteDatabase? = null
        val DB_NAME: String = "notes.db"

        fun getInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, DB_NAME).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun NoteDao(): NoteDao
}