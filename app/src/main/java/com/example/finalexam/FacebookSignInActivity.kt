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

class FacebookSignInActivity : AppCompatActivity() {

    private lateinit var FacebookSignInGoBackButton : ImageView
    private lateinit var FacebookSignInEmailEditText : EditText
    private lateinit var FacebookSignInPasswordEditText : EditText
    private lateinit var FacebookSignInNextButton : TextView

    private val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_sign_in)
        init()
        FacebookSignInListeners()

    }


    private fun init() {
        FacebookSignInGoBackButton = findViewById(R.id.FacebookSignInGoBackButton)
        FacebookSignInEmailEditText = findViewById(R.id.FacebookSignInEmailEditText)
        FacebookSignInPasswordEditText = findViewById(R.id.FacebookSignInPasswordEditText)
        FacebookSignInNextButton = findViewById(R.id.FacebookSignInNextButton)
    }



    private fun FacebookSignInListeners() {
        FacebookSignInGoBackButton.setOnClickListener{
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
        FacebookSignInNextButton.setOnClickListener {
            val email = FacebookSignInEmailEditText.text.toString()
            val password = FacebookSignInPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, LogInActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }


}