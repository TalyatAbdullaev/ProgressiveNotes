package com.example.progressivenotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivenotes.database.room.Note
import com.example.progressivenotes.databinding.NoteItemBinding

class NoteAdapter(var notes: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    inner class NoteViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        with(holder){
            with(notes[position]){
                binding.tvTitle.text = this.title
                binding.tvDescription.text = this.description
            }
        }
    }

    override fun getItemCount(): Int = notes.size
}