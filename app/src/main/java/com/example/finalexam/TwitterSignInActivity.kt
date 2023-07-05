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

class TwitterSignInActivity : AppCompatActivity() {

    private lateinit var TwitterSignInGoBackButton : ImageView
    private lateinit var TwitterSignInEmailEditText : EditText
    private lateinit var TwitterSignInPasswordEditText : EditText
    private lateinit var TwitterSignInNextButton : TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_sign_in)
        init()
        TwitterSignInListeners()

    }
    private fun init() {
        TwitterSignInGoBackButton = findViewById(R.id.TwitterSignInGoBackButton)
        TwitterSignInEmailEditText = findViewById(R.id.TwitterSignInEmailEditText)
        TwitterSignInPasswordEditText = findViewById(R.id.TwitterSignInPasswordEditText)
        TwitterSignInNextButton = findViewById(R.id.TwitterSignInNextButton)
    }


    private fun TwitterSignInListeners() {
        TwitterSignInGoBackButton.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
        TwitterSignInNextButton.setOnClickListener {
            val email = TwitterSignInEmailEditText.text.toString()
            val password = TwitterSignInPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, LogInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}