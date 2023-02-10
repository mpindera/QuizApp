package com.example.quizappgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizappgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginIntent = Intent(this, LoginActivity::class.java)
        val registerIntent = Intent(this, RegisterActivity::class.java)

        binding.buttonLogin.setOnClickListener {
            startActivity(loginIntent)
            finish()
        }
        binding.buttonRegister.setOnClickListener {
            startActivity(registerIntent)
            finish()
        }
    }
}


