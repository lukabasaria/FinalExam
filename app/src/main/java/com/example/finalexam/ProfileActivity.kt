package com.example.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.UserInfoActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private val firebaseAuth = Firebase.auth
    private val db = FirebaseDatabase.getInstance().getReference("userInfo")
    private val auth = FirebaseAuth.getInstance()

    private lateinit var ProfileUsernameTextView : TextView
    private lateinit var URLEditText : EditText
    private lateinit var ProfileUsernameEditText : EditText
    private lateinit var profileChangeProfileAvatarButton : TextView
    private lateinit var profileChangePasswordButton : TextView
    private lateinit var profileLogOutButton : TextView
    private lateinit var ProfilePictureImageView : ImageView
    private lateinit var ProfileActivityMessagesVideoButton :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        ProfileListeners()


        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo : UserInfoActivity = snapshot.getValue(UserInfoActivity::class.java) ?: return
                ProfileUsernameTextView.text = userInfo.username
                Glide.with(this@ProfileActivity).load(userInfo.url).into(ProfilePictureImageView)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "cancelled", Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun init() {
        ProfileUsernameTextView = findViewById(R.id.ProfileUsernameTextView)
        URLEditText = findViewById(R.id.URLEditText)
        ProfileUsernameEditText = findViewById(R.id.ProfileUsernameEditText)
        profileChangeProfileAvatarButton = findViewById(R.id.profileChangeProfileAvatarButton)
        profileChangePasswordButton = findViewById(R.id.profileChangePasswordButton)
        profileLogOutButton = findViewById(R.id.profileLogOutButton)
        ProfilePictureImageView = findViewById(R.id.ProfilePictureImageView)
        ProfileActivityMessagesVideoButton = findViewById(R.id.ProfileActivityMessagesVideoButton)

    }


    private fun ProfileListeners() {

        ProfileActivityMessagesVideoButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        profileChangeProfileAvatarButton.setOnClickListener {
            val url = URLEditText.text.toString()
            val username = ProfileUsernameEditText.text.toString()
            val userinfo = UserInfoActivity(username, url)
            db.child(auth.currentUser?.uid!!).setValue(userinfo)
        }
        profileLogOutButton.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        profileChangePasswordButton.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }


    }

    private fun replaceFragment(messageFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame,messageFragment).commit()

    }

}