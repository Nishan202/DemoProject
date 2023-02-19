package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    private lateinit var firstName: TextView
    private lateinit var lastName: TextView
    private lateinit var mail: TextView
    private lateinit var phoneNo: TextView
    private lateinit var btn: Button
    private lateinit var btn2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firstName = findViewById(R.id.firstNameInf)
        lastName = findViewById(R.id.lastNameInf)
        mail = findViewById(R.id.emailInf)
        phoneNo = findViewById(R.id.phoneInf)
        btn2 = findViewById(R.id.louOut)
        // get data from intent

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


        // for Logout
        btn2.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@Profile, MainActivity::class.java))
            finish()
        }

    }
}