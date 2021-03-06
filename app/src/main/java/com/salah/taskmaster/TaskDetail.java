package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Task> tasksList = new ArrayList<>();
        RoomDB database;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myTask", MODE_PRIVATE);
//        String savedTask = sharedPreferences.getString("task", "");
        Intent intent = getIntent();
        String savedTask = intent.getStringExtra("detailTitle");
        int targetId = intent.getIntExtra("taskId", 0) - 1;
        System.out.println(targetId);
        setTitle(savedTask);

        ActionBar actionBar = getSupportActionBar();
        ((ActionBar) actionBar).setHomeAsUpIndicator(R.drawable.mybutton);
        actionBar.setDisplayHomeAsUpEnabled(true);

        database = RoomDB.getInstance(this);
        tasksList = database.mainDao().getAll();
        String sBody = tasksList.get(targetId).getBody();
        String sState = tasksList.get(targetId).getState();
        String sCity = tasksList.get(targetId).getName_city();
        System.out.println("DetailDetailDetailDetailDetailDetailDetailDetailDetailDetail");
        System.out.println(sCity);
        TextView bodyTv = TaskDetail.this.findViewById(R.id.detail_body);
        TextView stateTv = TaskDetail.this.findViewById(R.id.detail_state);
        bodyTv.setText(sBody);
        stateTv.setText(sState);
        TextView location = findViewById(R.id.textViewLocation);
        location.setText(sCity);


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
