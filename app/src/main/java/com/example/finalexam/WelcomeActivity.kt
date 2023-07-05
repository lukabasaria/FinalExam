package com.example.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class WelcomeActivity : AppCompatActivity() {

    private lateinit var WelcomeLogInButton : TextView
    private lateinit var WelcomeSignUpButton : TextView
    private lateinit var WelcomeGoogleButton : TextView
    private lateinit var WelcomeFacebookButton : TextView
    private lateinit var WelcomeTwitterButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        init()
        WelcomeListeners()
    }


    private fun init() {
        WelcomeLogInButton = findViewById(R.id.WelcomeLogInButton)
        WelcomeSignUpButton = findViewById(R.id.WelcomeSignUpButton)
        WelcomeGoogleButton = findViewById(R.id.WelcomeGoogleButton)
        WelcomeFacebookButton = findViewById(R.id.WelcomeFacebookButton)
        WelcomeTwitterButton = findViewById(R.id.WelcomeTwitterButton)
    }



    private fun WelcomeListeners() {
        WelcomeLogInButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        WelcomeSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        WelcomeGoogleButton.setOnClickListener{
            startActivity(Intent(this, GoogleSignInActivity::class.java))
        }
        WelcomeFacebookButton.setOnClickListener {
            startActivity(Intent(this, FacebookSignInActivity::class.java))
        }
        WelcomeTwitterButton.setOnClickListener {
            startActivity(Intent(this, TwitterSignInActivity::class.java))
        }
    }

}