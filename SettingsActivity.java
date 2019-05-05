package com.example.samplechatapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.example.samplechatapplication.utils.SharePreferenceData;
import com.example.samplechatapplication.utils.UtilClass;

import java.util.List;
import java.util.Set;

/*
this class is used to changed settings for morse switch from main activity
 */
public class SettingsActivity extends AppCompatActivity {
    private Switch mSwitch;
    private Switch mSwitch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mSwitch=findViewById(R.id.switch1);
        mSwitch.setChecked(SharePreferenceData.INSTANCE.getBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_FLAG(),false));
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    SharePreferenceData.INSTANCE.setBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_FLAG(),true);
                }else{
                    SharePreferenceData.INSTANCE.setBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_FLAG(),false);
                }
            }
        });

        mSwitch1=findViewById(R.id.switch2);
        mSwitch1.setChecked(SharePreferenceData.INSTANCE.getBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_VIBRATION(),false));
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    SharePreferenceData.INSTANCE.setBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_VIBRATION(),true);
                }else{
                    SharePreferenceData.INSTANCE.setBooleanPreference(SettingsActivity.this, UtilClass.INSTANCE.getSWITCH_VIBRATION(),false);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
