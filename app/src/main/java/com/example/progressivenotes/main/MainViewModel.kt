package com.example.progressivenotes.main

import android.app.Application
import android.content.Context
import android.icu.text.CaseMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.progressivenotes.database.room.Note
import com.example.progressivenotes.database.room.NoteDatabase

class MainViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var notes: LiveData<List<Note>>
    private val db: NoteDatabase? = NoteDatabase.getInstance(application.applicationContext)

    fun saveNote(caption: String, description: String) {
        val note: Note = Note()

    }


}