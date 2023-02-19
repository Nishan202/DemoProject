package com.example.demoproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TodoList : AppCompatActivity(), MyAdapter.TodoAdapterClicks {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Todo>
    /**/
    private val nodeList = ArrayList<String>()
    private lateinit var db: FirebaseFirestore
    private lateinit var databaseRef: FirebaseAuth
    private lateinit var fabIcon: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf()
        //aadapter.setListener(this)
        db = FirebaseFirestore.getInstance()
        databaseRef = FirebaseAuth.getInstance()
        fabIcon = findViewById(R.id.fab)

        getUserData()

        fabIcon.setOnClickListener{
            val intent = Intent(this, TodoModify::class.java)
            startActivity(intent)
        }
    }

    private fun getUserData(){

        newArrayList= arrayListOf()
        db.collection("todoList").get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    for (data in it.documents){
                        val todoList: Todo? = data.toObject(Todo::class.java)
//                        if (todoList != null){
//                            newArrayList.add(todoList)
//                            /**/
//                            nodeList.add(data.id.toString())
//                        }
                        newArrayList.add(todoList!!)
                        /**/
                        nodeList.add(data.id)
                    }
                    /**/
                    var aadapter = MyAdapter(newArrayList)
                    newRecyclerView.adapter = aadapter
                    aadapter.setTodoAdapterClicks(object : MyAdapter.TodoAdapterClicks{
                        override fun onDeleteBtnClick(todoList: Todo) {
                            TODO("Not yet implemented")
                        }

                        override fun onClick(position: Int) {
                            val nodePath: String = nodeList[position]
                            val intent = Intent()
                            intent.putExtra("todo_id",nodePath)
                            setResult(2, intent)
                            finish()
                        }

                    })
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    private val itemResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val todoo = it.data?.getSerializableExtra("note") as Todo
            if (todoo!=null){

            }
        }
    }

    override fun onDeleteBtnClick(todoList: Todo) {

            db.collection("todoList").document(todoList.userId.toString())
                .delete()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show()
                        Log.d("TodoList", "Button clicked")
                    }else{
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }

    }

    override fun onClick(todoList: Int) {
        TODO("Not yet implemented")
    }
}
