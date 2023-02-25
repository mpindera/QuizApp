package com.example.quizappgame.account.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizappgame.R
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    // Initialize Firebase and properties
    private lateinit var auth: FirebaseAuth
    private lateinit var registerUsername: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerButton: Button

    private lateinit var registerUsernameText: String
    private lateinit var registerEmailText: String
    private lateinit var registerPasswordText: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        /*
        init TextView from activity_login

        get Instance from FirebaseAuth

        find by ID from activity_login
        */

        val changeToLogin = findViewById<TextView>(R.id.change_to_login)

        auth = FirebaseAuth.getInstance()

        registerUsername = findViewById(R.id.register_username)
        registerEmail = findViewById(R.id.register_email)
        registerPassword = findViewById(R.id.register_password)
        registerButton = findViewById(R.id.button_Register)

        /*
        On click button Check if login and password isn't null.

        Next:

        Register user with username, email and password:
        if we don't write password or email say: Enter Username/Email/Password

        if username , email and password are fill then we register to Firebase:
            if we already have account then say: You have already account
            if we don't have say: Your account has to be created
       TODO make better register.
        */

        registerButton.setOnClickListener {
            if (checkingTextRegister()) {

                registerUsernameText = registerUsername.text.toString()
                registerEmailText = registerEmail.text.toString()
                registerPasswordText = registerPassword.text.toString()

                auth.createUserWithEmailAndPassword(registerEmailText, registerPasswordText)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Your account has to be created", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnCanceledListener {
                        Toast.makeText(this, "You have already account", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Enter Username/Email/Password", Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    this, "Password must contains at least 6 characters", Toast.LENGTH_SHORT
                ).show()
            }
        }

        // if we click in login text then we intent to LoginActivity.

        changeToLogin.setOnClickListener {
            val intentToLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentToLoginActivity)
            finish()
        }
    }

    private fun checkingTextRegister(): Boolean {
        if (registerUsernameText.isNotEmpty() && registerEmailText.isNotEmpty() && registerPasswordText.isNotEmpty()) {
            return true
        }
        return false
    }
}