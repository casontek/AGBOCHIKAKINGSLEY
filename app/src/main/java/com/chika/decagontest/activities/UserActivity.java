package com.chika.decagontest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chika.decagontest.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity {
    CircleImageView img;
    TextView tv_countries, tv_colors, tv_gender, tv_names, tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //initializes widgets
        init();
        //display user info
        userDetails(getIntent());

    }

    private void init(){
        tv_colors = findViewById(R.id.colors);
        tv_countries = findViewById(R.id.countries);
        tv_gender = findViewById(R.id.gender);
        tv_names = findViewById(R.id.names);
        tv_date  = findViewById(R.id.user_date);
        img = findViewById(R.id.user_avater);
    }

    private void userDetails(Intent intent) {
        tv_names.setText(intent.getStringExtra("names"));
        tv_date.setText(intent.getStringExtra("createdAt"));
        tv_gender.setText(intent.getStringExtra("gender"));

        String url = intent.getStringExtra("avater");
        if (!TextUtils.isEmpty(url))
            Glide.with(this).load(url).into(img);

        String[] colors = intent.getStringArrayExtra("colors");
        String[] countries = intent.getStringArrayExtra("countries");
        String col = "";
        String cou = "";

        //display colors
        for (String c : colors){
            col = col + ", " + c;
        }
        tv_colors.setText(col);

        //display countries
        for(String c: countries){
            cou = cou + ", " + c;
        }
        tv_countries.setText(cou);
    }

}