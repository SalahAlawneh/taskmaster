package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add Task");
        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setHomeAsUpIndicator(R.drawable.mybutton);
        actionBar.setDisplayHomeAsUpEnabled(true);

        database = RoomDB.getInstance(this);

        Button addTaskButton = AddTask.this.findViewById(R.id.button3);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTask.this, "The Task added", Toast.LENGTH_LONG).show();
                EditText titleEt = AddTask.this.findViewById(R.id.editTextTextPersonName);
                EditText bodyEt = AddTask.this.findViewById(R.id.editTextTextPersonName2);
                EditText stateEt = AddTask.this.findViewById(R.id.editTextTextPersonName3);
                String sTitle = titleEt.getText().toString().trim();
                String sBody = bodyEt.getText().toString().trim();
                String sState = stateEt.getText().toString().trim();
                Task data = new Task();
                data.setTitle(sTitle);
                data.setBody(sBody);
                data.setState(sState);
                database.mainDao().insert(data);
                Intent intent = new Intent();
                startActivity(intent);
                finish();


            }
        });
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