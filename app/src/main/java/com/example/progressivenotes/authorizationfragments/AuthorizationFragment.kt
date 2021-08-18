package com.example.progressivenotes.authorizationfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.progressivenotes.R
import com.example.progressivenotes.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {
    private lateinit var binding: FragmentAuthorizationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false   )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bntSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_signUpFragment)
        }

        binding.btnSignIn.setOnClickListener {

        }

        binding.btnForgotPass.setOnClickListener {

        }
    }
}