package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        setTitle("Task Detail");

        TextView showTaskTitle;
        showTaskTitle = TaskDetail.this.findViewById(R.id.textView4);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myTask", MODE_PRIVATE);
        String savedTask = sharedPreferences.getString("taskOne", "");
        showTaskTitle.setText(savedTask);
    }
}