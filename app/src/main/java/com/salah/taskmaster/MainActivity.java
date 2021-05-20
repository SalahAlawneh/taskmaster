package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        sharedPreferences = getApplicationContext().getSharedPreferences("myUserPreferences", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("settingUserName", "");
        username.setText(savedUsername + "'s tasks");

        Button taskOne = MainActivity.this.findViewById(R.id.button);
        taskOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("myTask", MODE_PRIVATE);
                String taskOne = "Workout";
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("taskOne", taskOne);
                editor.commit();
                Toast.makeText(MainActivity.this, "data saved.", Toast.LENGTH_LONG).show();
                Intent goToDetails = new Intent(MainActivity.this, TaskDetail.class);
                startActivity(goToDetails);

            }
        });


    }

}