package com.example.demoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User

class TodoList : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var storeData: FirebaseFirestore
    private lateinit var newArrayList: ArrayList<Todo>
    private lateinit var myAdapter: MyAdapter
//    lateinit var titleId: Array<String>
//    lateinit var body: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        /*
        titleId = arrayOf(
            "Excersize", "Study", "Sprots", "Gym"
        )

        body = arrayOf(
            "WE HAVE TO DO EXCERSIZE",
            "WE HAVE TO DO STUDY",
            "WE DO SPORTS ALSO",
            "WE WILL DO GYM"
        )
*/
        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList= arrayListOf()

        myAdapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = myAdapter


        getUserData()
    }

    private fun getUserData(){
//        for (i in titleId.indices){
//            val todos = Todo(titleId[i], body[i])
//            newArrayList.add(todos)
//        }
//        newRecyclerView.adapter = MyAdapter(newArrayList)

        storeData = FirebaseFirestore.getInstance()
        storeData.collection("todoList")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error !=  null){
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            newArrayList.add(dc.document.toObject(Todo::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })
    }
}
