package com.example.quizappgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val changeToRegister = findViewById<TextView>(R.id.change_to_create)

        changeToRegister.setOnClickListener {
            val intentToRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegisterActivity)
            finish()
        }
    }
}