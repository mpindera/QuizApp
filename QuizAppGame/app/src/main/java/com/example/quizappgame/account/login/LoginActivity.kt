package com.example.quizappgame.account.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizappgame.R
import com.example.quizappgame.admin.panel.MainAdminPanel
import com.example.quizappgame.user.panel.MainPanelUser
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // init Firebase and also from activity_login
    private lateinit var auth: FirebaseAuth
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button

    private lateinit var loginEmailText: String
    private lateinit var loginPasswordText: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*
        init TextView from activity_login

        get Instance from FirebaseAuth

        find by ID from activity_login
        */
        val changeToRegister = findViewById<TextView>(R.id.change_to_create)

        auth = FirebaseAuth.getInstance()

        loginEmail = findViewById(R.id.login_email)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.button_login)

        /*
        On click button Check if login and password isn't null.

        Next:

        Login user with email and password:
        if we don't write password or email say: Wrong Email/password

        if login and password are fill then checking if we are in Firebase:
        if we are in Firebase(true) then we are checking if our ID is equal to admin ID
            if yes: Intent to MainAdminPanel. And say: Hello Admin.
            if no: Intent to MainPanelUser. And say: Welcome
        if we aren't in Firebase(false) then say: You don't have account
        */

        loginButton.setOnClickListener {
            if (checkingTextLogin()) {
                loginEmailText = loginEmail.text.toString()
                loginPasswordText = loginPassword.text.toString()
                auth.signInWithEmailAndPassword(loginEmailText, loginPasswordText)
                    .addOnSuccessListener {
                        if (FirebaseAuth.getInstance().uid == "tiNQaAQ1ZSR3eN28rPs1lYP9yV42") {

                            Toast.makeText(this, "Hello, Admin", Toast.LENGTH_SHORT)
                                .show()
                            val intentToAdminPanel = Intent(this, MainAdminPanel::class.java)
                            startActivity(intentToAdminPanel)

                        } else {

                            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT)
                                .show()
                            val intentPanelUser = Intent(this, MainPanelUser::class.java)
                            startActivity(intentPanelUser)

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

        // if we click in register text then we intent to RegisterActivity.

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