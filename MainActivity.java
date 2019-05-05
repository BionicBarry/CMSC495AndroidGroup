package com.example.samplechatapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/*
this activity is the main launching activity and is being used to navigate throughout the app and show the list of the chats and settings.
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Chat> list = new ArrayList<>();
    private Adapter adapter;
    private static int CONTACTS_REQUEST_CODE = 0;
    private static int CHAT_ACTIVITY_REQUEST_CODE = 1;
    private int MY_PERMISSIONS_REQUEST_CODE=100;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission();
        }
        toolbar.setTitle("MyChats");
        setSupportActionBar(toolbar);
        //createChatList();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivityForResult(intent, CONTACTS_REQUEST_CODE);
            }
        });
        this.adapter = new Adapter(this);
        adapter.setList(list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == CONTACTS_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        String phone = data.getStringExtra("phone");
                        String name = data.getStringExtra("name");
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("name", name);
                        startActivityForResult(intent, 1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == CHAT_ACTIVITY_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        ArrayList<Message> message = (ArrayList<Message>) data.getSerializableExtra("message");
                        String senderName = data.getExtras().getString("contact_name");
                        String sender = senderName;
                        String messageText = message.get(message.size() - 1).message;
                        Chat chat = new Chat();
                        chat.id = i;
                        chat.name = sender;
                        chat.message = messageText;
                        chat.imagePath = R.drawable.sample1;
                        list.add(0, chat);
                        adapter.setList(list);
                        adapter.setMessageList(message);
                        i++;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == 2) {
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        ArrayList<Message> message = (ArrayList<Message>) data.getSerializableExtra("message");
                        String messageText = message.get(message.size() - 1).message;
                        adapter.changeLastMessageForDefaultChat(messageText);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
    }
    static int i=0;
   /* private void createChatList() {
        Chat chat = new Chat();
        chat.id=i;
        chat.name = "Name1";
        chat.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat.imagePath = R.drawable.sample1;
        i++;

        Chat chat2 = new Chat();
        chat2.id=i;
        chat2.name = "Name2";
        chat2.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat2.imagePath = R.drawable.sample2;
        i++;

        Chat chat3 = new Chat();
        chat3.id=i;
        chat3.name = "Name3";
        chat3.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat3.imagePath = R.drawable.sample3;
        i++;


        Chat chat4 = new Chat();
        chat4.id=i;
        chat4.name = "Name4";
        chat4.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat4.imagePath = R.drawable.sample4;
        i++;

        Chat chat5 = new Chat();
        chat5.id=i;
        chat5.name = "Name5";
        chat5.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat5.imagePath = R.drawable.sample1;
        i++;

        Chat chat6 = new Chat();
        chat.id=i;
        chat6.name = "Name6";
        chat6.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat6.imagePath = R.drawable.sample2;
        i++;

        Chat chat7 = new Chat();
        chat7.id=i;
        chat7.name = "Name7";
        chat7.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat7.imagePath = R.drawable.sample3;
        i++;

        Chat chat8 = new Chat();
        chat8.id=i;
        chat8.name = "Name8";
        chat8.message =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut a";
        chat8.imagePath = R.drawable.sample4;
        i++;

        list.add(chat);
        list.add(chat2);
        list.add(chat3);
        list.add(chat4);
        list.add(chat5);
        list.add(chat6);
        list.add(chat7);
        list.add(chat8);
    }*/

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



    protected void checkPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                this,Manifest.permission.VIBRATE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this,Manifest.permission.VIBRATE)){
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Camera vibration" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.VIBRATE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.VIBRATE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
            Toast.makeText(this,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 100:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) &&
                                (grantResults[0]
                                        + grantResults[1]
                                        == PackageManager.PERMISSION_GRANTED
                                )
                ){
                    // Permissions are granted
                    Toast.makeText(this,"Permissions granted.",Toast.LENGTH_SHORT).show();
                }else {
                    // Permissions are denied
                    Toast.makeText(this,"Permissions denied.",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
