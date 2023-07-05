package com.example.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    private lateinit var LogInGoBackButton : ImageView
    private lateinit var LogInEmailEditText : EditText
    private lateinit var LogInPasswordEditText : EditText
    private lateinit var LogInForgotPasswordTextView : TextView
    private lateinit var LogInGoToProfilePageButton : TextView
    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        init()
        LogInListeners()

    }


    private fun init() {
        LogInGoBackButton = findViewById(R.id.LogInGoBackButton)
        LogInEmailEditText = findViewById(R.id.LogInEmailEditText)
        LogInPasswordEditText = findViewById(R.id.LogInPasswordEditText)
        LogInForgotPasswordTextView = findViewById(R.id.LogInForgotPasswordTextView)
        LogInGoToProfilePageButton = findViewById(R.id.LogInGoToProfilePageButton)

    }


    private fun LogInListeners() {
        LogInGoToProfilePageButton.setOnClickListener {
            val email = LogInEmailEditText.text.toString()
            val password = LogInPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

                }
            }
        }
        LogInForgotPasswordTextView.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        LogInGoBackButton.setOnClickListener{
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

    }


}