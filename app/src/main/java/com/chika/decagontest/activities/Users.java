package com.chika.decagontest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chika.decagontest.R;
import com.chika.decagontest.adapters.UserAdapter;
import com.chika.decagontest.models.User;
import com.chika.decagontest.networks.RestClient;
import com.chika.decagontest.networks.UserAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Users extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter adapter;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        //display back navigation icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //initialize widgets
        init();
        //load users
        getUsers();

    }

    private void init(){
        recyclerView = findViewById(R.id.user_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new UserAdapter(this, new ArrayList<>());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        bar = findViewById(R.id.progress);
        bar.setVisibility(View.GONE);
    }

    private void getUsers(){
        //shows progress bar
        bar.setVisibility(View.VISIBLE);

        //makes a network communication to fetch users data
        UserAPI api = RestClient.getInstance().create(UserAPI.class);
        Call<List<User>> usersCall = api.getUsers();
        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //hides progress bar
                bar.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    adapter = new UserAdapter(getBaseContext(), response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Communication error.", Toast.LENGTH_SHORT).show();
                Log.d("network_error", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}