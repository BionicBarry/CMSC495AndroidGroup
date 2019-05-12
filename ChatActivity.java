package com.example.SMorSe495;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.SMorSe495.broadcast.MyReceiver;
import com.example.SMorSe495.utils.Alerts;
import com.example.SMorSe495.utils.UtilClass;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.samplechatapplication.R;

/*
This activity is used for the chat detail screen, when user tap on the chat list
 */

public class ChatActivity extends AppCompatActivity implements MessageReciver{

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ArrayList<String> mSpinnerList = new ArrayList<String>();
    @Nullable
    private String contactName;
    private String contactPhone;
    private Spinner spinner;
    @Nullable
    private List<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        contactName = getIntent().getStringExtra("name");
        contactPhone = getIntent().getStringExtra("phone");
        messageList = MainActivity.stringListHashMapMessages.get(contactPhone);

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(contactName);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.sample1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ArrayList<Message> messagesList = new ArrayList<>();
        mSpinnerList.add("Urgency");
        mSpinnerList.add("None");
        mSpinnerList.add("Urgent");
        mSpinnerList.add("Emergency");
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mSpinnerList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
              //      new Alerts(ChatActivity.this).findUrgency(i - 1, spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (messageList == null || messageList.isEmpty() || contactName.isEmpty()) {

        } else {
            messagesList.clear();
            messagesList.addAll(messageList);
        }

        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        mMessageRecycler.setLayoutManager(layoutManager);
        mMessageAdapter.setList(messagesList);
        mMessageRecycler.setAdapter(mMessageAdapter);
        setEditTextMessage();
        MyReceiver.messageReciver=this;
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
                    message.createdAt = new Date().getTime()+"";
                    message.folderName="sent";
                    mMessageAdapter.setMessage(message,mMessageRecycler);
                    edittext_chatbox.setText("");

                    try {
                        Intent intentSms = new Intent();
                        intentSms.putExtra(UtilClass.INSTANCE.getSPINNER_SELECTION(), spinner.getSelectedItemPosition() - 1);
                        intentSms.putExtra(UtilClass.INSTANCE.getSPINNER_VALUE(), spinner.getSelectedItem().toString());
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                ChatActivity.this, 234324243, intentSms, 0);
                        //Sending the Text Message
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(contactPhone, null, text, pendingIntent, null);
                        Toast.makeText(ChatActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(ChatActivity.this, "Message not sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyReceiver.messageReciver=null;
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onReciveMessage(@NotNull Message message) {
        //Added functionality to read text messages and convert them into morse
        //I am not sure if this is the best place to put them as of now
        Alerts alert = new Alerts(this.getApplicationContext());
        mMessageAdapter.setMessage(message,mMessageRecycler);
        //Morse Relay Parser
        alert.findUrgency(0,message.getMessage());
        mMessageRecycler.scrollToPosition(messageList.size());
    }
}
