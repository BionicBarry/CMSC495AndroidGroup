//The following is a skelton outline of what the main activity should be like

package com.example.bionicbarry.smorse;

//import whatever libraries are required
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class SMorSe extends AppCompatActivity {
    //Alerts class needed for recieving messages
    Alerts alerter = new Alerts();
    userSettings uset = new userSettings();

    //method activated when a message is recieved via SMS
    public void messageRecieved(){

    }

    //method for sending a message, tieing directly into User Input to create a new message class and sending it
    protected void sendMessage(){
        //Message Sender
        String sender, recipient, body;
        int urgency;
        //creates new message class
        Message msg = new Message(sender, recipient, body, urgency);
        msg.send(); //not supported yet
    }

    //Method that brings up the user settings
    protected void userSettings(){
        //UI allowing user to change their settings
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smor_se);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
