package com.example.samplechatapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_single_item.view.*

class Adapter : RecyclerView.Adapter<Adapter.ChatViewHolder>() {

    private lateinit var list: List<Chat>
    private var messageList: ArrayList<Message>? = null

    override fun onBindViewHolder(p0: ChatViewHolder, p1: Int) {
        p0.bind(p1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_single_item, parent, false)

        return ChatViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<Chat>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setMessageList(message: ArrayList<Message>?) {
        this.messageList = message
    }

    inner class ChatViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            with(itemView) {
                nameTextView?.text = list[position].name
                messageTextView?.text = list[position].message
                imageView.setBackgroundResource(list[position].imagePath)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ChatActivity::class.java)
                    intent.putExtra("messageList", messageList)
                    intent.putExtra("name", list[position].name)
                    context.startActivity(intent)
                }
            }
        }
    }
}