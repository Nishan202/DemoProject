package com.example.demoproject

import android.widget.TextView
import org.w3c.dom.Text
import java.util.SimpleTimeZone
import kotlin.properties.Delegates

class UserModel {
    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var phoneNo: String
    private lateinit var email: String


    constructor(firstName: String, lastName: String, phoneNo: String, email: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNo = phoneNo
        this.email = email
    }

    constructor()
}