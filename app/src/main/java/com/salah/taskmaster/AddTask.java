package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
                data.setFileKey(myFile);
                database.mainDao().insert(data);
                Intent intent = new Intent();
                startActivity(intent);
                finish();


            }
        });

        findViewById(R.id.upload_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("*/*");
                startActivityForResult(intent, 1111);

            }
        });
    }

    String myFile = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111) {
            File file = new File(getApplicationContext().getFilesDir(), "uploads");
            if (resultCode == RESULT_OK) {
                myFile = "start";
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
            }
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                try {
                    OutputStream out = new FileOutputStream(file);
                    try {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        uploadFile(file);
                    } finally {
                        out.close();
                    }
                } finally {
                    inputStream.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void uploadFile(File file) {
        Amplify.Storage.uploadFile(
                myFile,
                file,
                result -> Log.d("my result", result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
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