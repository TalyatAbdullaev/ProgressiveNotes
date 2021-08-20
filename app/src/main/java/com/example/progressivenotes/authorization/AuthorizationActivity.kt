package com.example.progressivenotes.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.progressivenotes.R
import com.example.progressivenotes.databinding.ActivityAuthoriztionBinding
import com.google.cloud.audit.AuthorizationInfo

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthoriztionBinding
    private lateinit var viewModel: AuthorizationViewModel
    private lateinit var viewModelFactory: AuthorizationViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthoriztionBinding.inflate(layoutInflater)
        viewModelFactory = AuthorizationViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthorizationViewModel::class.java)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            val code = binding.etPassword.text.toString()
            viewModel.signInWithCode(code)
        }

        binding.btnGetPass.setOnClickListener {
            val phone: String = binding.etPhone.text.toString()
            if(phone.length < 12) {
                viewModel.showToast("Вы не заполнили номер телефона")
            } else {
                viewModel.phoneOptions(phone)
            }
        }
    }
}