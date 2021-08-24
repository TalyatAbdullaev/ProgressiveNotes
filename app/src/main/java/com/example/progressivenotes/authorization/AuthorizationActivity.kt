package com.example.progressivenotes.authorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.progressivenotes.databinding.ActivityAuthoriztionBinding
import com.example.progressivenotes.main.MainActivity
import com.example.progressivenotes.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthoriztionBinding
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthoriztionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializations()

        binding.btnSendPhone.setOnClickListener {
            val phone = binding.etPhone.text.toString().trim()
            if(phone.length < 12) {
                Toast.makeText(applicationContext, "Вы не заполнили номер телефона", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("TAG", phone)
                phoneVerification(phone)
            }
        }

        binding.btnSendCode.setOnClickListener {
            val code = binding.etCode.text.toString().trim()
            signInWithCode(code)
        }
    }

    private fun initializations() {
        auth = FirebaseAuth.getInstance()
        Firebase.auth.setLanguageCode("ru")
        binding.llPhone.visibility = View.VISIBLE
        binding.llVerification.visibility = View.GONE
    }

    private fun phoneVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d("TAG", "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w("TAG", "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "Invalid request")
            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG", "The SMS quota for the project has been exceeded")
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d("TAG", "onCodeSent:$verificationId")
            Toast.makeText(applicationContext, "SMS сообщение было отправлено на ваш номер телефона", Toast.LENGTH_SHORT).show()
            binding.llVerification.visibility = View.VISIBLE
            binding.llPhone.visibility = View.GONE

            val phone = binding.etPhone.text.toString().trim()
            binding.tvVerifPhone.text = String.format(resources.getString(R.string.enter_verification_code), phone)

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun signInWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText (applicationContext, "Вы успешно авторизировались!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java), )
                    //val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.d("TAG", "The verification code entered was invalid")
                        Toast.makeText( applicationContext, "Вы ввели неверный пароль!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}