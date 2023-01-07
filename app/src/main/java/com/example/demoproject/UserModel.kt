package com.example.demoproject

import android.widget.TextView
import org.w3c.dom.Text
import java.util.SimpleTimeZone

class UserModel {
//    private lateinit var firstName: TextView
//    private lateinit var lastName: TextView
//    private lateinit var email: TextView
//    private lateinit var phoneNo: TextView

    class UserModel(firstname: String, lastname: String, mail: String, phone: Long){
        var firstName = firstname
        var lastName = lastname
        var email = mail
        var phoneNo = phone
    }

}