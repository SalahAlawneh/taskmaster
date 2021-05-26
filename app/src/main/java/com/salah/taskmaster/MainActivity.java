package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<Task> tasks = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;


//    String tasksTitle[] = {"Workout", "Study", "Learn E"};
//    Task taskOne = new Task("Workout", "10 push ups", "complete");
//    Task taskTwo = new Task("Study", "Study Java", "assigned");
//    Task taskThree = new Task("Learn E", "listening and writing", "in progress");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView username;
        SharedPreferences sharedPreferences;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButt = MainActivity.this.findViewById(R.id.add_task_button);
        addTaskButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
//                goToAddTask.putExtra("key","value");
                MainActivity.this.startActivity(goToAddTask);
            }
        });

        Button allTasksButt = MainActivity.this.findViewById(R.id.all_tasks_button);
        allTasksButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
                MainActivity.this.startActivity(goToAllTasks);
            }
        });

        Button mainActivitySettingButton = MainActivity.this.findViewById(R.id.main_setting_button);
        mainActivitySettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSetting = new Intent(MainActivity.this, Setting.class);
                startActivity(goToSetting);
            }
        });

        username = MainActivity.this.findViewById(R.id.main_show_username);
//        sharedPreferences = getApplicationContext().getSharedPreferences("myUserPreferences", MODE_PRIVATE);
//        String savedUsername = sharedPreferences.getString("settingUserName", "username");
//        username.setText(savedUsername + "'s tasks");
        Intent intent = getIntent();
        String intentSavedName = intent.getStringExtra("Name");
        username.setText(intentSavedName);
        recyclerView = findViewById(R.id.recylerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RecyclerAdapter(this, tasksTitle);
//        recyclerView.setAdapter(adapter);


        // initialize database
        database = RoomDB.getInstance(this);
        // store database value in data list
        tasks = database.mainDao().getAll();
//        database.mainDao().nukeTable();
        // initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager((this));
        // set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        // initialize adapter
        adapter = new RecyclerAdapter(tasks, MainActivity.this);
        // Set adapter
        recyclerView.setAdapter(adapter);
//
//        Button taskOne = MainActivity.this.findViewById(R.id.button);
//        taskOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences1 = getSharedPreferences("myTask", MODE_PRIVATE);
//                String taskOne = "Workout";
//                SharedPreferences.Editor editor = sharedPreferences1.edit();
//                editor.putString("task", taskOne);
//                editor.commit();
//                Toast.makeText(MainActivity.this, "data saved.", Toast.LENGTH_LONG).show();
//                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
//                startActivity(goToDetails);
//
//            }
//
//
//        });
//
//        Button tasTwo = MainActivity.this.findViewById(R.id.button2);
//        tasTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences1 = getSharedPreferences("myTask", MODE_PRIVATE);
//                String taskTwo = "Study";
//                SharedPreferences.Editor editor = sharedPreferences1.edit();
//                editor.putString("task", taskTwo);
//                editor.commit();
//                Toast.makeText(MainActivity.this, "data saved.", Toast.LENGTH_LONG).show();
//                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
//                startActivity(goToDetails);
//
//            }
//
//
//        });
//
//        Button tasThree = MainActivity.this.findViewById(R.id.button4);
//        tasThree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences1 = getSharedPreferences("myTask", MODE_PRIVATE);
//                String taskTwo = "Learn English";
//                SharedPreferences.Editor editor = sharedPreferences1.edit();
//                editor.putString("task", taskTwo);
//                editor.commit();
//                Toast.makeText(MainActivity.this, "data saved.", Toast.LENGTH_LONG).show();
//                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
//                startActivity(goToDetails);
//
//            }
//
//
//        });


    }


}