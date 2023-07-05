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

class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var GoogleSignInGoBackButton : ImageView
    private lateinit var GoogleSignInEmailEditText : EditText
    private lateinit var GoogleSignInPasswordEditText : EditText
    private lateinit var GoogleSignInNextButton : TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sign_in)
        init()
        GoogleSignInListeners()

    }

    private fun init() {
        GoogleSignInGoBackButton = findViewById(R.id.GoogleSignInGoBackButton)
        GoogleSignInEmailEditText = findViewById(R.id.GoogleSignInEmailEditText)
        GoogleSignInPasswordEditText = findViewById(R.id.GoogleSignInPasswordEditText)
        GoogleSignInNextButton = findViewById(R.id.GoogleSignInNextButton)
    }



    private fun GoogleSignInListeners() {
        GoogleSignInGoBackButton.setOnClickListener{
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
        GoogleSignInNextButton.setOnClickListener {
            val email = GoogleSignInEmailEditText.text.toString()
            val password = GoogleSignInPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, LogInActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

}