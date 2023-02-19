package com.example.demoproject

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.demoproject.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View
import java.io.ByteArrayOutputStream

class TodoModify : AppCompatActivity() {

    private lateinit var title: EditText
    private lateinit var body: EditText
    private lateinit var saveBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var firebaseAuth: FirebaseAuth
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_modify)

        title = findViewById(R.id.todoTitle)
        body = findViewById(R.id.todoBody)
        saveBtn = findViewById(R.id.save)
        deleteBtn = findViewById(R.id.delete)
        progressBar = findViewById(R.id.progressB)
        saveBtn.setOnClickListener { createTodo() }
        deleteBtn.setOnClickListener { deleteTask() }

//        if (arguments != null){
//            todo = Todo(arguments?.getString("taskId").toString())
//        }

    }

    private fun createTodo(){
        val title = title.text.toString()
        val body = body.text.toString()
        val todo = hashMapOf(
            "name" to title,
            "details" to body
        )
        val db = FirebaseFirestore.getInstance()
        //val userId = FirebaseAuth.getInstance().currentUser!!.uid

        if (title.isNotBlank() && body.isNotBlank()){
            progressBar.visibility = android.view.View.VISIBLE
            db.collection("todoList").document().set(todo)
                .addOnSuccessListener {
                    Toast.makeText(this, "Todo add successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Todo add failed", Toast.LENGTH_SHORT).show()
                }
            progressBar.visibility = android.view.View.GONE
        }
        else{
            Toast.makeText(this, "Need to fill Todo name and details", Toast.LENGTH_SHORT).show()
        }
    }

    private val itemResultLauncher = registerForActivityResult<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        if (result.resultCode == 2){
            val intent = result.resultData
            if (intent != null){
                var nodeId = intent.getStringExtra("todo_id").toString()
            }
            val db = FirebaseFirestore.getInstance()
            //val todoId = FirebaseAuth.getInstance().currentUser!!.uid

            db.collection("todoList").document()
                .get()
                .addOnSuccessListener {
                    if (it.exists()){

                    }else {
                        Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun updateTask(view: View){

    }

    private fun deleteTask(){
        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        db.collection("todoList").document(userId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Task deleted successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    private fun readFireStore() {
        val db = FirebaseFirestore.getInstance()
        //val resultView: TextView = findViewById(R.id.resultView)
        db.collection("users")
            .get()
            .addOnCompleteListener {
                val result: StringBuffer = StringBuffer()
                if (it.isSuccessful) {

                }
            }
    }
//    companion object{
//        @JvmStatic
//        fun getDataFromList(userId: String, name: String, details: String) = Todo().apply {
//            var arguments = Bundle().apply {
//                putString("taskId", userId)
//                putString("taskName", name)
//                putString("taskDetails", details)
//            }
//        }
//    }

}

