package com.example.progressivenotes.main

import androidx.lifecycle.LiveData
import com.example.progressivenotes.database.room.Note

class MainViewModel {
    private lateinit var notes: LiveData<List<Note>>


}