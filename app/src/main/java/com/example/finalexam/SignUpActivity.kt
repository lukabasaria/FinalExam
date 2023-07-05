package com.example.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private val firebaseAuth = Firebase.auth

    private lateinit var SignUpEmailEditText : EditText
    private lateinit var SignUpPasswordEditText : EditText
    private lateinit var SignUpNextPageButton : TextView
    private lateinit var SignUpRepeatPasswordEditText : EditText
    private lateinit var SignUpGoBackButton : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
        SignUpListeners()

    }


    private fun init() {
        SignUpEmailEditText = findViewById(R.id.SignUpEmailEditText)
        SignUpPasswordEditText = findViewById(R.id.SignUpPasswordEditText)
        SignUpNextPageButton = findViewById(R.id.SignUpNextPageButton)
        SignUpRepeatPasswordEditText = findViewById(R.id.SignUpRepeatPasswordEditText)
        SignUpGoBackButton = findViewById(R.id.SignUpGoBackButton)
    }



    private fun SignUpListeners() {
        SignUpGoBackButton.setOnClickListener{
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }


        SignUpNextPageButton.setOnClickListener {
            val email = SignUpEmailEditText.text.toString()
            val password = SignUpPasswordEditText.text.toString()
            val repeatPassword = SignUpRepeatPasswordEditText.text.toString()

            if(repeatPassword!=password){
                Toast.makeText(this, "wrong password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty() || password.isEmpty()  || password.length < 10 || password.contains(' ')) {
                Toast.makeText(this, "Email, password or repeat password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Congratulations! Now login!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LogInActivity::class.java))
                } else {
                    Toast.makeText(this, "something's wrong, try again", Toast.LENGTH_SHORT).show()
                }

            }

        }


    }


}