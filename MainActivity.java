package com.example.samplechatapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;


/*
this activity is the main launching activity and is being used to navigate throughout the app and show the list of the chats and settings.
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Chat> list = new ArrayList<>();
    private Adapter adapter;
    private static int CONTACTS_REQUEST_CODE = 0;
    private static int CHAT_ACTIVITY_REQUEST_CODE = 1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MyChats");
        setSupportActionBar(toolbar);
        createChatList();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivityForResult(intent, CONTACTS_REQUEST_CODE);
            }
        });
        this.adapter = new Adapter();
        adapter.setList(list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACTS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String phone = data.getStringExtra("phone");
                String name = data.getStringExtra("name");
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
            }
        } else if (requestCode == CHAT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Message> message = (ArrayList<Message>) data.getSerializableExtra("message");
                String sender = message.get(0).sender.nickname;
                String messageText = message.get(0).message;
                Chat chat = new Chat();
                chat.name = sender;
                chat.message = messageText;
                chat.imagePath = R.drawable.sample1;
                list.add(0, chat);
                adapter.setList(list);
                adapter.setMessageList(message);
            }
        }
    }

    private void createChatList() {
        Chat chat = new Chat();
        chat.name = "Name1";
        chat.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat.imagePath = R.drawable.sample1;

        Chat chat2 = new Chat();
        chat2.name = "Name2";
        chat2.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat2.imagePath = R.drawable.sample2;

        Chat chat3 = new Chat();
        chat3.name = "Name3";
        chat3.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat3.imagePath = R.drawable.sample3;

        Chat chat4 = new Chat();
        chat4.name = "Name4";
        chat4.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat4.imagePath = R.drawable.sample4;

        Chat chat5 = new Chat();
        chat5.name = "Name5";
        chat5.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat5.imagePath = R.drawable.sample1;

        Chat chat6 = new Chat();
        chat6.name = "Name6";
        chat6.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat6.imagePath = R.drawable.sample2;

        Chat chat7 = new Chat();
        chat7.name = "Name7";
        chat7.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat7.imagePath = R.drawable.sample3;

        Chat chat8 = new Chat();
        chat8.name = "Name8";
        chat8.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat8.imagePath = R.drawable.sample4;

        list.add(chat);
        list.add(chat2);
        list.add(chat3);
        list.add(chat4);
        list.add(chat5);
        list.add(chat6);
        list.add(chat7);
        list.add(chat8);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}