package com.example.demoproject

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val todoList: ArrayList<Todo>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View, listener: TodoAdapterClicks?) : RecyclerView.ViewHolder(itemView){
        //val todo_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val todoTitle: TextView = itemView.findViewById(R.id.title)
        val todoBody: TextView = itemView.findViewById(R.id.body)
        val todoDeleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)

        init {
            itemView.setOnClickListener{
                listener?.onClick(adapterPosition)
            }
        }
    }
    private var mListener: TodoAdapterClicks? = null
    private lateinit var newArrayList: ArrayList<Todo>
    private lateinit var fullList: ArrayList<Todo>
    fun setTodoAdapterClicks(listener: TodoAdapterClicks){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = todoList[position]
//        holder.todoTitle.text = currentItem.name
//        holder.todoBody.text = currentItem.details

        // For decode base64 image view
        /*
        val bytes = android.util.Base64.decode(currentItem.itemImg, android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        holder.itemImg.setImageBitmap(bitmap)
        */

        with(todoList[position]){
            holder.todoTitle.text = this.name
            holder.todoBody.text = this.details

//            holder.todoEditBtn.setOnClickListener {
//                mListener?.onClick(this)
//            }
            holder.todoDeleteBtn.setOnClickListener {
                mListener?.onDeleteBtnClick(this)
            }

        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    interface TodoAdapterClicks {
        fun onDeleteBtnClick(todoList: Todo)
        fun onClick(position: Int)
    }

}