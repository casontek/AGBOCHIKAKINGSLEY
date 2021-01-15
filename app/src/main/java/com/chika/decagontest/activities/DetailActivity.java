package com.chika.decagontest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chika.decagontest.R;

public class DetailActivity extends AppCompatActivity {
    TextView tv_names, tv_email, tv_job, tv_country, tv_gender, tv_bio,
            tv_car_model, tv_car_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //sets back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //init widgets
        init();
        //display data
        bindUI(getIntent());

    }

    private void init(){
        tv_names = findViewById(R.id.names);
        tv_email = findViewById(R.id.email);
        tv_job = findViewById(R.id.job);
        tv_car_color = findViewById(R.id.car_color);
        tv_car_model = findViewById(R.id.car_model_year);
        tv_country = findViewById(R.id.country);
        tv_gender = findViewById(R.id.gender);
        tv_bio = findViewById(R.id.bio);
    }

    private void bindUI(Intent intent){
        String fullName = intent.getStringExtra("fname") + " " + intent.getStringExtra("lname");
        String country = "Country: " + intent.getStringExtra("country");
        String gender = "Gender: " + intent.getStringExtra("gender");
        String car = "Car: " + intent.getStringExtra("car_model") + ", " + intent.getStringExtra("car_model_year");

        tv_names.setText(fullName);
        tv_email.setText(intent.getStringExtra("email"));
        tv_country.setText(country);
        tv_job.setText(intent.getStringExtra("job"));
        tv_car_model.setText(car);
        tv_car_color.setText(intent.getStringExtra("car_color"));
        tv_gender.setText(gender);
        tv_bio.setText(intent.getStringExtra("bio"));

    }

}