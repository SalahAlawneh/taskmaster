package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<Task> tasks = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        com.google.android.gms.tasks.Task
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_main);
        System.out.println("salaha=====================================================================================================================================================");
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
//                        System.out.println("inner=====================================================================================================================================================");
//                        System.out.println(task);
//                        if (!task.isSuccessful()) {
//                            System.out.println("راسب=====================================================================================================================================================");
//
//                            Log.w("this is my tag", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//                        System.out.println("ناجح=====================================================================================================================================================");
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        // Log and toast
//                        Log.d("token", token);
//                    }
//                });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull @NotNull com.google.android.gms.tasks.Task task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG salah", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = (String) task.getResult();
                        Log.d("token", token);
                    }
                });
        System.out.println("salaha=====================================================================================================================================================");

        TextView username;
        SharedPreferences sharedPreferences;


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

        Intent intent = getIntent();
        String intentSavedName = intent.getStringExtra("Name");
        username.setText(intentSavedName);
        recyclerView = findViewById(R.id.recylerview);


        // initialize database
        database = RoomDB.getInstance(this);
        // store database value in data list
        tasks = database.mainDao().getAll();
        // in order to update the table
//        database.mainDao().reset(tasks);
        // initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager((this));
        // set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        // initialize adapter
        adapter = new RecyclerAdapter(tasks, MainActivity.this);
        // Set adapter
        recyclerView.setAdapter(adapter);


//        try {
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }
//        Sign up ===================================================================
        MainActivity.this.findViewById(R.id.sigup_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignup = new Intent(MainActivity.this, Signup.class);
                startActivity(goToSignup);
            }
        });


//        Login ===================================================================

        MainActivity.this.findViewById(R.id.main_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(MainActivity.this, Login.class);
                startActivity(goToLogin);
            }
        });

//        Sign out ===================================================================

        MainActivity.this.findViewById(R.id.main_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> Log.i("AuthQuickstart", "Signed out globally"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

        System.out.println("salaha=====================================================================================================================================================");

    }


}