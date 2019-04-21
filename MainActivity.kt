package com.example.samplechatapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list = ArrayList<Chat>()
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar?.title = "MyChats"
        setSupportActionBar(findViewById(R.id.toolbar))
        createChatList()
        fab?.setOnClickListener {
            val intent = Intent(this, ContactsActivity::class.java)
            startActivityForResult(intent, CONTACTS_REQUEST_CODE)
        }
        this.adapter = Adapter()
        adapter.setList(list)
        recyclerView?.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CONTACTS_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val phone = data?.getStringExtra("phone")
                    val name = data?.getStringExtra("name")
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra("phone", phone)
                    intent.putExtra("name", name)
                    startActivityForResult(intent, 1)
                }
            }
            CHAT_ACTIVITY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val message = data?.getSerializableExtra("message") as? ArrayList<Message>
                    val sender = message?.get(0)?.sender?.nickname
                    val messageText = message?.get(0)?.message
                    val chat = Chat(sender, messageText, R.drawable.sample1)
                    list.add(0, chat)
                    adapter.setList(list)
                    adapter.setMessageList(message)
                }
            }
        }
    }

    private fun createChatList() {
        val chat = Chat(
            "Name1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample1
        )
        val chat2 = Chat(
            "Name2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample2
        )
        val chat3 = Chat(
            "Name3",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample3
        )
        val chat4 = Chat(
            "Name4",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample4
        )
        val chat5 = Chat(
            "Name5",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample1
        )
        val chat6 = Chat(
            "Name6",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample2
        )
        val chat7 = Chat(
            "Name7",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample3
        )
        val chat8 = Chat(
            "Name8",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a",
            R.drawable.sample4
        )
        list.add(chat)
        list.add(chat2)
        list.add(chat3)
        list.add(chat4)
        list.add(chat5)
        list.add(chat6)
        list.add(chat7)
        list.add(chat8)
    }

    companion object {
        const val CONTACTS_REQUEST_CODE = 0
        const val CHAT_ACTIVITY_REQUEST_CODE = 1
    }
}
