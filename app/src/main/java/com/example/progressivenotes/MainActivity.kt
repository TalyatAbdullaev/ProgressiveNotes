package com.example.progressivenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.progressivenotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val navController = binding.navHostFragment.findNavController()

        binding.botNavView.setOnClickListener { item ->
            when(item.id) {
                R.id.notes -> navController.navigate(R.id.notesFragment)
                R.id.users -> navController.navigate(R.id.usersFragment)
                R.id.settings -> navController.navigate(R.id.settingsFragment)
            }
        }
    }
}