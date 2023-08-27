package com.kr.zentech

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.kr.zentech.auth.SaveToken
import com.kr.zentech.databinding.ActivityLoginBinding
import com.kr.zentech.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.loginUser(username, password)
        }
        viewModel.loginResponseLiveData.observe(this) { loginResponse ->
            val token = loginResponse.token
            val id = loginResponse.id
            if (token != null && id != null) {
                SaveToken.saveTokenAndId(this, token, id)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
        }
        viewModel.errorLiveData.observe(this) { errorMessage ->
            showError(errorMessage)
        }



    }
    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }


}



