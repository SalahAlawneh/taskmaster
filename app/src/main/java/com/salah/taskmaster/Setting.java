package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");

        Button saveButton = findViewById(R.id.setting_save_button);
        EditText settingUserName = findViewById(R.id.setting_username);
        sharedPreferences = getSharedPreferences("myUserPreferences", MODE_PRIVATE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting.this, "data saved.", Toast.LENGTH_LONG).show();
                String usernameStr = settingUserName.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("settingUserName", usernameStr);
                editor.apply();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("Name", usernameStr);
                startActivity(intent);
                finish();

            }
        });
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setHomeAsUpIndicator(R.drawable.mybutton);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}