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

class ChangePasswordActivity : AppCompatActivity() {

    private val firebaseAuth = Firebase.auth
    private lateinit var ChangePasswordGoBackButton : ImageView
    private lateinit var ChangePasswordCurrentPasswordEditText : EditText
    private lateinit var ChangePasswordNewPasswordEditText : EditText
    private lateinit var ChangePasswordGoToProfilePageButton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        init()
        ChangePasswordListeners()

    }


    private fun init(){
        ChangePasswordGoBackButton = findViewById(R.id.ChangePasswordGoBackButton)
        ChangePasswordCurrentPasswordEditText = findViewById(R.id.ChangePasswordCurrentPasswordEditText)
        ChangePasswordNewPasswordEditText = findViewById(R.id.ChangePasswordNewPasswordEditText)
        ChangePasswordGoToProfilePageButton = findViewById(R.id.ChangePasswordGoToProfilePageButton)
    }



    private fun ChangePasswordListeners() {
        ChangePasswordGoBackButton.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        ChangePasswordGoToProfilePageButton.setOnClickListener {
            val currentPassword = ChangePasswordCurrentPasswordEditText.text.toString()
            val newPassword = ChangePasswordNewPasswordEditText.text.toString()

            if (newPassword==currentPassword){
                Toast.makeText(this, "use other password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (newPassword.isEmpty()){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "password updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LogInActivity::class.java))
                } else {
                    Toast.makeText(this, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}