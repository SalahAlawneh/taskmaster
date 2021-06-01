package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.button_login_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.login_username);
                EditText password = findViewById(R.id.login_password);
                String usernameSt = username.getText().toString();
                String passwordSt = password.getText().toString();
                System.out.println(usernameSt + passwordSt);
                Amplify.Auth.signIn(
                        usernameSt,
                        passwordSt,
                        result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent goToMainActivity = new Intent(Login.this,MainActivity.class);
            }
        });
    }
}