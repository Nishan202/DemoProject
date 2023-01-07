package com.example.demoproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val todoList: ArrayList<Todo>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.todoTitle.text = currentItem.name
        holder.todoBody.text = currentItem.details
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val todoTitle: TextView = itemView.findViewById(R.id.title)
        val todoBody: TextView = itemView.findViewById(R.id.body)
    }
}