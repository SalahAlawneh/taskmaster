package com.salah.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

public class AddTask extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    RoomDB database;
    String name_City = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
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
                System.out.println("savename_Cityname_Cityname_Cityname_Cityname_Cityname_City");
                System.out.println(name_City);
                data.setName_city(name_City);
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.i("Location service", "latitude is==>" + location.getLatitude());
                            Log.i("Location service", "longitude is==>" + location.getLongitude());
                            Geocoder geocoder = new Geocoder(AddTask.this, Locale.getDefault());
                            try {
                                List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 3);
                                name_City = address.get(0).getCountryName();
                                System.out.println("salahname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_Cityname_City");
                                System.out.println(name_City);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

        Intent intent = getIntent();
        Uri uploadedImage = intent.getData();
        if (uploadedImage != null) {
            myFile = "salah";
            File file = new File(getApplicationContext().getFilesDir(), "uploads");

            try {
                InputStream inputStream = getContentResolver().openInputStream(uploadedImage);
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

            }

        }
    }

    String myFile = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111) {
            File file = new File(getApplicationContext().getFilesDir(), "uploads");
            if (resultCode == RESULT_OK) {
                myFile = "start";
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