package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {

    private lateinit var submitBtn: Button
    private lateinit var email: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        submitBtn = findViewById(R.id.sendBtn)
        email = findViewById(R.id.emailAddress)

        submitBtn.setOnClickListener {
            val mail = email.text.toString().trim{ it <= ' '}
            if (mail.isEmpty()){
                Toast.makeText(this, "Please enter email address", Toast.LENGTH_LONG).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Please check your mail to reset your password", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@ForgotPassword, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
                }
            }
        }
    }
}