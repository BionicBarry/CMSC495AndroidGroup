package com.example.samplechatapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private var mMessageRecycler: RecyclerView? = null
    private lateinit var mMessageAdapter: MessageListAdapter
    private var contactName: String? = null
    private var messageList: List<Message>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""

        contactName = intent.getStringExtra("name")
        messageList = intent.getSerializableExtra("messageList") as? List<Message>
        nameTextView?.text = contactName
        imageView?.setImageResource(R.drawable.sample1)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val messagesList = ArrayList<Message>()

        if (messageList.isNullOrEmpty() && contactName.isNullOrEmpty()) {
            val user1 = User("Sam", R.drawable.sample1, 1111)
            val user2 = User("Alex", R.drawable.sample2, 1234)
            val user3 = User("Alex", R.drawable.sample2, 1111)
            val user4 = User("Sam", R.drawable.sample1, 1234)

            val message1 = Message("Hello, how are you?", user1, "2:30 PM")
            val message2 = Message("Hello, how are you?", user2, "2:35 PM")
            val message3 = Message("Whats going on?", user3, "3:30 PM")
            val message4 = Message("Hello, how are you?", user4, "3:35 PM")

            messagesList.add(message1)
            messagesList.add(message2)
            messagesList.add(message3)
            messagesList.add(message4)
        } else {
            messagesList.clear()
            messageList?.let { messagesList.addAll(it) }
        }

        mMessageRecycler = findViewById(R.id.reyclerview_message_list)
        mMessageAdapter = MessageListAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mMessageRecycler?.layoutManager = layoutManager
        mMessageAdapter.setList(messagesList)
        mMessageRecycler?.adapter = mMessageAdapter
        setEditTextMessage()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("message", mMessageAdapter.mMessageList)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun setEditTextMessage() {
        button_chatbox_send?.setOnClickListener {
            val text = edittext_chatbox?.text
            if (!text.isNullOrEmpty()) {
                val user = User(contactName ?: "Sam", R.drawable.sample1, 1234)
                val message = Message(text.toString(), user, "2:30 PM")
                mMessageAdapter.setMessage(message)
                edittext_chatbox?.text?.clear()
            }
        }
    }
}
