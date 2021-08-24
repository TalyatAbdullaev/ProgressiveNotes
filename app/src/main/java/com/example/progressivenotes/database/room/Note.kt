package com.example.progressivenotes.database.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note {
    fun Note() {
        @PrimaryKey(autoGenerate = true)
        val id: Int
        val title: String
        val description: String
    }
}
