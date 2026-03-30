package com.example.startupmentor

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.startupmentor.data.PreferenceManager
import com.example.startupmentor.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PreferenceManager(this)

        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                val name = binding.etFullName.text.toString()
                val email = binding.etEmail.text.toString()
                val stage = binding.spinnerStage.selectedItem.toString()

                prefManager.setLoggedIn(true)
                prefManager.setUserData(name, email, stage)

                startActivity(Intent(this, OnboardingActivity::class.java))
                finishAffinity()
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true
        val name = binding.etFullName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (name.isEmpty()) {
            binding.tilFullName.error = "Enter your full name"
            isValid = false
        } else {
            binding.tilFullName.error = null
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Enter a valid email"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        if (password.isEmpty() || password.length < 6) {
            binding.tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }

        if (confirmPassword != password) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            isValid = false
        } else {
            binding.tilConfirmPassword.error = null
        }

        if (!binding.cbTerms.isChecked) {
            Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }
}