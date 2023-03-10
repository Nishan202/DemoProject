package com.example.demoproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.SpannableString
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.demoproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.time.ZoneId

class SignUp : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var phoneNo: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private var passwordVisible: Boolean = false
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    

    /*private val emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.LastName)
        email = findViewById(R.id.emailAddress)
        phoneNo = findViewById(R.id.phoneNumber)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        //viewProf = findViewById(R.id.viewProfile)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // for email validation
        /*
        fun validateEmail(view: View?) {

            if (Patterns.EMAIL_ADDRESS.matcher(emailPattern).matches()){
                Toast.makeText(applicationContext, "Valid email address", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(applicationContext, "Invalid email address",
                    Toast.LENGTH_SHORT).show()
            }
        }
         */

//        viewProf.setOnClickListener {
//            val intent = Intent(this@SignUp, Profile::class.java)
//            startActivity(intent)
//            Log.d("SignUp", "Button click successful")
//        }

//        val btn: Button = findViewById(R.id.nextBtn)
        binding.nextBtn.setOnClickListener{
            signUpUser()
        }

        // for link of sign in page
        clickable()
        // for eye button
        visiblePassword()

    }

    private fun clickable() {
        //val textView: TextView = findViewById(R.id.signInTxtView)
        val textView: TextView = findViewById(R.id.signInTxtView)
        val spannableString: SpannableString by lazy { SpannableString("Don't have an account? Sign up") }
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {
                val intent = Intent(this@SignUp,  MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        spannableString.setSpan(clickableSpan, 0, spannableString.length, 0)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun callActivity(){

        // get text from edit texts
        val firstName  = firstName.text.toString()
        val lastName = lastName.text.toString()
        val email = email.text.toString()
        val phoneNumber = phoneNo.text.toString().toLong()

        //intent to start activity
        val intent = Intent(this, ContactsContract.Profile::class.java)
        intent.putExtra("FIRST_NAME", firstName)
        intent.putExtra("LAST_NAME", lastName)
        intent.putExtra("EMAIL", email)
        intent.putExtra("PHONE_NUMBER", phoneNumber)
        startActivity(intent)
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun visiblePassword(){
        binding.password.setOnTouchListener(View.OnTouchListener { _: View, event: MotionEvent ->
            val right = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.password.right - binding.password.compoundDrawables[right].bounds
                        .width()
                ) {
                    val selection = binding.password.selectionEnd
                    if (passwordVisible) {
                        // set drawable image here
                        binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_off_24,
                            0
                        )
                        // for hide password
                        binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordVisible = false
                    } else {
                        // set drawable image here
                        binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_24,
                            0
                        )
                        // for hide password
                        binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        passwordVisible = true
                    }
                    binding.password.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false

        })

        binding.confirmPassword.setOnTouchListener(View.OnTouchListener { _: View, event: MotionEvent ->
            val right = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.confirmPassword.right - binding.confirmPassword.compoundDrawables[right].bounds
                        .width()
                ) {
                    val selection = binding.confirmPassword.selectionEnd
                    if (passwordVisible) {
                        // set drawable image here
                        binding.confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_off_24,
                            0
                        )
                        // for hide password
                        binding.confirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordVisible = false
                    } else {
                        // set drawable image here
                        binding.confirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_baseline_visibility_24,
                            0
                        )
                        // for hide password
                        binding.confirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        passwordVisible = true
                    }
                    binding.confirmPassword.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false

        })
    }

    // for store data in firestore
    /*
    private fun saveFireStore(firstName: String, lastName: String, email: String, phoneNo: Long, password: String, confirmPassword: String){
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstName"] = firstName
        user["lastName"] = lastName
        user["phoneNo"] = phoneNo
        user["email"] = email
        user["password"] = password
        user["confirmPassword"] = confirmPassword

        // db.collection ('database name')
        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                //Toast.makeText(this, "Profile update successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                //Toast.makeText(this, "Profile update failed", Toast.LENGTH_SHORT).show()
            }
        //readFireStore()
    }

    // for read the database
    private fun readFireStore(){
        val db = FirebaseFirestore.getInstance()
        val resultView: TextView = findViewById(R.id.resultView)
        db.collection("users")
            .get()
            .addOnCompleteListener{
                    val result: StringBuffer = StringBuffer()
                if (it.isSuccessful){
                    for (document in it.result!!){
                        result.append(document.data.getValue("firstName")).append(" ")
                            .append(document.data.getValue("lastName")).append("\n")
                            .append(document.data.getValue("phoneNo")).append("\n")
                            .append(document.data.getValue("email")).append("\n\n")
                    }
                    //resultView.text = result
                }
            }
    }
*/
    private fun signUpUser(){
        val firstNm  = binding.firstName.text.toString()
        val lastNm = binding.LastName.text.toString()
        val mail = binding.emailAddress.text.toString().trim{it <= ' '}
        val phoneNm: String = binding.phoneNumber.text.toString()
        val password = binding.password.text.toString().trim{it <= ' '}
        val confirmPassword = binding.confirmPassword.text.toString().trim { it <= ' ' }

        // check text fields are empty or not
        if (firstNm.isNotEmpty() && lastNm.isNotEmpty() && mail.isNotEmpty() && phoneNm.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            if (password == confirmPassword){
                binding.progressBar.visibility = View.VISIBLE
                firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener {
                    // if registration is successfully done
                    if (it.isSuccessful){
                        Toast.makeText(this, "Successfully Registration", Toast.LENGTH_LONG).show()
                        val firebaseUser: FirebaseUser = it.result!!.user!!
//                        if (user != null) {
//                            db.collection("users").document(user.uid).set(UserModel(firstName, lastName, email, phoneNo))
//                        }
                        val intent = Intent(this@SignUp, LandingPage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("USER_ID", firebaseUser.uid)
                        intent.putExtra("EMAIL", firebaseUser.email)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }
            else{
                Toast.makeText(this, "Password and confirm password is not match", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "First Name, Last Name, Email, Phone Number and Password is required", Toast.LENGTH_SHORT).show()
            Log.d("SignUp", "It's working fine")
        }
    }

}