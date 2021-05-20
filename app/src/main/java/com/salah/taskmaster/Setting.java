package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText settingUserName;
        SharedPreferences sharedPreferences;
        Button saveButton;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");

        saveButton = findViewById(R.id.setting_save_button);
        settingUserName = findViewById(R.id.setting_username);
        sharedPreferences = getSharedPreferences("myUserPreferences", MODE_PRIVATE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String usernameStr = settingUserName.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("settingUserName", usernameStr);
                editor.commit();
                Toast.makeText(Setting.this,"data saved.",Toast.LENGTH_LONG).show();

            }
        });
    }
}