package com.example.quizappgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button

    private lateinit var loginEmailText: String
    private lateinit var loginPasswordText: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val changeToRegister = findViewById<TextView>(R.id.change_to_create)

        auth = FirebaseAuth.getInstance()

        loginEmail = findViewById(R.id.login_email)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.button_login)

        loginButton.setOnClickListener {
            if (checkingTextLogin()) {
                loginEmailText = loginEmail.text.toString()
                loginPasswordText = loginPassword.text.toString()
                auth.signInWithEmailAndPassword(loginEmailText, loginPasswordText)
                    .addOnSuccessListener {
                        if (FirebaseAuth.getInstance().uid == "tiNQaAQ1ZSR3eN28rPs1lYP9yV42") {
                            Toast.makeText(this, "Hello, Admin", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainAdminPanel::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, MainActivityUser::class.java)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "You don't have account", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(this, "Wrong Email/password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        changeToRegister.setOnClickListener {
            val intentToRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegisterActivity)
            finish()
        }
    }

    private fun checkingTextLogin(): Boolean {
        if (loginEmail.text.isNotEmpty() && loginPassword.text.isNotEmpty()) {
            return true
        }
        return false
    }
}