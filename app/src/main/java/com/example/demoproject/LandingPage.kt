package com.example.demoproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.BadTokenException
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class LandingPage : AppCompatActivity() {
    private lateinit var mail: TextView
    private lateinit var id: TextView
    private lateinit var viewList: Button
    private lateinit var logoutBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        id = findViewById(R.id.userId)
        mail = findViewById(R.id.userEmail)
        viewList = findViewById(R.id.todoView)
        val userId = intent.getStringExtra("USER_ID")
        val email = intent.getStringExtra("EMAIL")

        id.text = "User Id: $userId"
        mail.text = "Email: $email"

        logoutBtn = findViewById(R.id.logOut)

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        viewList.setOnClickListener {
            val intent = Intent(this, TodoList::class.java)
            startActivity(intent)
        }
    }
}