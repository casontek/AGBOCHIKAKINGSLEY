package com.chika.decagontest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import com.chika.decagontest.R;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize components
        init();

    }

    private void init(){
        MaterialButton btn_owners, btn_users;
        btn_users = findViewById(R.id.btn_find_users);
        btn_owners = findViewById(R.id.btn_find_cars);
        //listens to buttons clicks
        btn_owners.setOnClickListener(v -> findCarsOwner());
        btn_users.setOnClickListener(v -> fetchPeople());
    }

    private void findCarsOwner(){
        //try and check if the Cars owners file exist
        try {
            InputStream in = getResources().openRawResource(R.raw.car_ownsers_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            //open the cars owners activity
            Intent i = new Intent(this, CarOwnersActivity.class);
            startActivity(i);
        }
        catch (Exception e){
            Log.d("file_error", e.getMessage());
            //tell the user that file does not exist
            Toast.makeText(getBaseContext(), "Car Owners data not found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchPeople(){
        //navigates to users list page
        Intent i = new Intent(this, Users.class);
        startActivity(i);
    }
    
}