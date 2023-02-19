package com.example.demoproject

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.demoproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*
import java.util.zip.Inflater


class MainActivity : AppCompatActivity() {

    /// Declaration of EditTexts and button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var logIn: Button
    private var count: Int = 3
    private var passwordVisible: Boolean = false
    lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var lmain: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Edit Text and button
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        logIn = findViewById(R.id.nextButton)
        auth = FirebaseAuth.getInstance()
        lmain = findViewById(R.id.mainl)
        progressBar = findViewById(R.id.progressBar)

        // call the validate function
        logIn.setOnClickListener {
//            validate(
//                email.text.toString(),
//                password.text.toString()
//            )
            logIn()

        }

        /// for clickable link
        clickable()

//        Thread.sleep(5000)
//        GlobalScope.launch {
//
//        }

        // for Password visibility
        visiblePassword()

    }


    private fun clickable(){

        val textView: TextView = findViewById(R.id.forgotPasswordTxtView)
        val textView2: TextView = findViewById(R.id.signUpTxtView)
        val spannableString = SpannableString("Forgot password")
        val spannableString2 = SpannableString("Don't have an account? Sign up")
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
//               Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity,  ForgotPassword::class.java)
                startActivity(intent)
                finish()
            }
        }

        val clickableSpan2 = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(this@MainActivity,  SignUp::class.java)
                startActivity(intent)
                finish()
            }
        }
        spannableString.setSpan(clickableSpan, 0, spannableString.length, 0)
        spannableString2.setSpan(clickableSpan2, 0, spannableString2.length, 0)
        textView.text = spannableString
        textView2.text = spannableString2
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView2.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun validate(userName: String, userPassword: String){
        if (userName == "nishansarkar100@gmail.com" && userPassword == "Nishan@123"){
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
        else{
            count --

            Toast.makeText(this, "No of attempts remaining $count", Toast.LENGTH_SHORT).show()
            if(count == 0){
                logIn.isEnabled = false
            }
        }
//        Toast.makeText(this, "Wait for 5 seconds, then the button will enable", Toast.LENGTH_LONG).show()
//        Thread.sleep(10000)
//        logIn.isEnabled = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun visiblePassword(){
        password.setOnTouchListener(View.OnTouchListener { _: View, event: MotionEvent ->
            val right = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= password.right - password.compoundDrawables[right].bounds
                        .width()
                ) {
                    val selection = password.selectionEnd
                    if (passwordVisible) {
                        // set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_off_24,
                            0
                        )
                        // for hide password
                        password.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordVisible = false
                    } else {
                        // set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_24,
                            0
                        )
                        // for hide password
                        password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        passwordVisible = true
                    }
                    password.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false

        })
    }

    private fun logIn(){
        val email = email.text.toString()
        val password = password.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val customSnackbar = Snackbar.make(lmain, "Successfully LoggedIn", Snackbar.LENGTH_LONG).setAction("Open",object : View.OnClickListener{
                        override fun onClick(v: View?) {
                            var intent = Intent(this@MainActivity, LandingPage::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("USER_ID", firebaseUser.uid)
                            intent.putExtra("EMAIL", firebaseUser.email)
                            //intent.putExtra("PHONE_NUMBER", firebaseUser.phoneNumber)
                            startActivity(intent)
                            finish()
                        }
                    })
                    customSnackbar.setActionTextColor(Color.WHITE)
                    val snackbarView = customSnackbar.view
                    snackbarView.setBackgroundColor(Color.GREEN)
                    val textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                    textView.setTextColor(Color.WHITE)
                    customSnackbar.show()
                } else {
                    Toast.makeText(this, "Invalid Credential", Toast.LENGTH_LONG).show()
                }
                progressBar.visibility = View.GONE
            }
        }
        else{
                Toast.makeText(this, "Email and Password is required", Toast.LENGTH_SHORT).show()
        }
    }


    // for still logged in the current account --> when user clicks on the apk file then if there any account logged in then the landing page will open directly
//    override fun onStart() {
//        super.onStart()
//        if (auth.currentUser != null) {
//            val intent = Intent(this, LandingPage::class.java)
//            startActivity(intent)
//        }
//        else{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }

}
