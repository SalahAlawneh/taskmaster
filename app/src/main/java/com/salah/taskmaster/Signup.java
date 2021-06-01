package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.sigup_button2).setOnClickListener(v -> {
            EditText username = findViewById(R.id.sigup_username);
            EditText password = findViewById(R.id.sigup_password);
            EditText email = findViewById(R.id.sigup_email);
            String usernameSt = username.getText().toString();
            String passwordSt = password.getText().toString();
            String emailSt = email.getText().toString();
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), emailSt)
                    .build();
            Amplify.Auth.signUp(usernameSt, passwordSt, options,
                    result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                    error -> Log.e("AuthQuickStart", "Sign up failed", error)
            );
            Intent goToConfirm = new Intent(this,VerificationCode.class);
            startActivity(goToConfirm);

        });


    }
}