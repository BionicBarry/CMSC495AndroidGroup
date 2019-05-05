package com.example.samplechatapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.samplechatapplication.utils.Alerts;
import com.example.samplechatapplication.utils.SharePreferenceData;
import com.example.samplechatapplication.utils.UtilClass;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/*
This activity is used for the chat detail screen, when user tap on the chat list
 */

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ArrayList<String> mSpinnerList=new ArrayList<String>();
    @Nullable
    private String contactName;
    @Nullable
    private List<Message> messageList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        contactName = getIntent().getStringExtra("name");
        messageList = (List<Message>) getIntent().getSerializableExtra("messageList");

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(contactName);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.sample1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ArrayList<Message> messagesList = new ArrayList<>();
      /* // Button button_chatbox_morse=findViewById(R.id.button_chatbox_morse);
        button_chatbox_morse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharePreferenceData.INSTANCE.getBooleanPreference(ChatActivity.this, UtilClass.INSTANCE.getSWITCH_FLAG(),false)){
                    Toast.makeText(ChatActivity.this,"chat box enable",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ChatActivity.this,"chat box disable",Toast.LENGTH_LONG).show();
                }
            }
        });*/

        mSpinnerList.add("Urgency");
        mSpinnerList.add("None");
        mSpinnerList.add("Urgent");
        mSpinnerList.add("Emergency");
        final Spinner spinner=findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,mSpinnerList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    new Alerts(ChatActivity.this).findUrgency(i-1,spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (messageList == null || messageList.isEmpty() || contactName.isEmpty()) {
           /* User user1 = new User();
            user1.nickname = "Sam";
            user1.profileUrl = R.drawable.sample1;
            user1.id = 1111;

            User user2 = new User();
            user2.nickname = "Alex";
            user2.profileUrl = R.drawable.sample2;
            user2.id = 1234;

            User user3 = new User();
            user3.nickname = "Alex";
            user3.profileUrl = R.drawable.sample2;
            user3.id = 1111;

            User user4 = new User();
            user4.nickname = "Sam";
            user4.profileUrl = R.drawable.sample1;
            user4.id = 1234;

            Message message1 = new Message();
            message1.message = "Hello, how are you?";
            message1.sender = user1;
            message1.createdAt = "2:30 PM";
            Message message2 = new Message();
            message2.message = "Hello, how are you?";
            message2.sender = user2;
            message2.createdAt = "2:35 PM";
            Message message3 = new Message();
            message3.message = "Whats going on?";
            message3.sender = user3;
            message3.createdAt = "3:30 PM";
            Message message4 = new Message();
            message4.message = "Hello, how are you?";
            message4.sender = user4;
            message4.createdAt = "3:35 PM";

            messagesList.add(message1);
            messagesList.add(message2);
            messagesList.add(message3);
            messagesList.add(message4);*/
        } else {
            messagesList.clear();
            messagesList.addAll(messageList);
        }

        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mMessageRecycler.setLayoutManager(layoutManager);
        mMessageAdapter.setList(messagesList);
        mMessageRecycler.setAdapter(mMessageAdapter);
        setEditTextMessage();
    }

    private void setEditTextMessage() {
        final Button button_chatbox_send = findViewById(R.id.button_chatbox_send);
        button_chatbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edittext_chatbox = findViewById(R.id.edittext_chatbox);
                String text = String.valueOf(edittext_chatbox.getText());
                if (!text.isEmpty()) {
                    User user = new User();
                    user.nickname = contactName != null ? contactName : "Sam";
                    user.profileUrl = R.drawable.sample1;
                    user.id = 1234;
                    Message message = new Message();
                    message.message = text;
                    message.sender = user;
                    message.createdAt = "2:30 PM";
                    mMessageAdapter.setMessage(message);
                    edittext_chatbox.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("message", mMessageAdapter.getMMessageList());
        intent.putExtra("contact_name", contactName);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
