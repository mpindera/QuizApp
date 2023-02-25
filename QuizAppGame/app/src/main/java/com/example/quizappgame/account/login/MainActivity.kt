package com.example.quizappgame.account.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizappgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // We init binding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init Intent to loginIntent and registerIntent
        val loginIntent = Intent(this, LoginActivity::class.java)
        val registerIntent = Intent(this, RegisterActivity::class.java)

        // if we click button Login then we go to LoginActivity
        binding.buttonLogin.setOnClickListener {
            startActivity(loginIntent)
        }

        // if we click button Register then we go to RegisterActivity
        binding.buttonRegister.setOnClickListener {
            startActivity(registerIntent)
        }
    }
}


