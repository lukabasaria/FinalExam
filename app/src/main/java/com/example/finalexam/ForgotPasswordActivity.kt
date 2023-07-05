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

class ForgotPasswordActivity : AppCompatActivity() {

    private val firebaseAuth = Firebase.auth

    private lateinit var ForgotPasswordGoBackButton : ImageView
    private lateinit var ForgotPasswordEmailEditText : EditText
    private lateinit var ForgotPasswordSendPasswordButton :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        init()
        ForgotPasswordListeners()

    }

    private fun init() {
        ForgotPasswordGoBackButton = findViewById(R.id.ForgotPasswordGoBackButton)
        ForgotPasswordEmailEditText = findViewById(R.id.ForgotPasswordEmailEditText)
        ForgotPasswordSendPasswordButton = findViewById(R.id.ForgotPasswordSendPasswordButton)
    }



    private fun ForgotPasswordListeners() {
        ForgotPasswordGoBackButton.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        ForgotPasswordSendPasswordButton.setOnClickListener {
            val email = ForgotPasswordEmailEditText.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener
            }
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Please, check your email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LogInActivity::class.java))

                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}