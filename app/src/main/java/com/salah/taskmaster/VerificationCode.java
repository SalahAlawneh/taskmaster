package com.salah.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class VerificationCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText confirmCode = findViewById(R.id.confirm_ET);
                String confirmCodeSt = confirmCode.getText().toString();
                EditText username = findViewById(R.id.confirm_username);
                String usernameSt = username.getText().toString();
                System.out.println("======================================================");
                System.out.println(confirmCodeSt);
                System.out.println("======================================================");
                Amplify.Auth.confirmSignUp(
                        usernameSt,
                        confirmCodeSt,
                        result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent goToLogin = new Intent(VerificationCode.this, Login.class);
                startActivity(goToLogin);
            }
        });
    }
}