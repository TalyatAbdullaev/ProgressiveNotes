package com.example.progressivenotes.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.progressivenotes.R
import com.example.progressivenotes.databinding.FragmentAddNoteBinding
import com.example.progressivenotes.main.MainActivity
import com.example.progressivenotes.main.MainViewModel

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnNextStep.setOnClickListener {
            val caption = binding.etCaption.text.toString()
            val description = binding.etDescription.text.toString()
            if(caption.isNotEmpty() && description.isNotEmpty()) {
                viewModel.saveNote(caption, description)
            } else {
                Toast.makeText(context, "Не заполнены поля Заголовок или Описание", Toast.LENGTH_SHORT).show()
            }
        }
    }
}