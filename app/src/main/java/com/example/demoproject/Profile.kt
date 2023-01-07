package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var email: TextView
    private lateinit var phoneNo: TextView
    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firstName = findViewById(R.id.firstNameInf)
        lastName = findViewById(R.id.lastNameInf)
        email = findViewById(R.id.emailInf)
        phoneNo = findViewById(R.id.phoneInf)
        btn = findViewById(R.id.todoView)
        // get data from intent
        /*
        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")
        val email = intent.getStringExtra("EMAIL")
        val phoneNumber = intent.getStringExtra("PHONE_NUMBER")


        val frstNm = findViewById<TextView>(R.id.firstNameInf)
        // setText
        frstNm.text = firstName

        val lstNm = findViewById<TextView>(R.id.lastNameInf)
        lstNm.text = lastName

        val eml = findViewById<TextView>(R.id.emailInf)
        eml.text = email

        val phn = findViewById<TextView>(R.id.phoneInf)
        phn.text = phoneNumber

        */

        btn.setOnClickListener {
            val intent = Intent(this, TodoList::class.java)
            startActivity(intent)
        }

        fun profileDetails(firstname: String, lastname: String, mail: String, phone: Long) {
            this.firstName
        }
    }

}