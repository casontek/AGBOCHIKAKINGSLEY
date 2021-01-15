package com.chika.decagontest.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chika.decagontest.R;
import com.chika.decagontest.adapters.CarOwnerAdapter;
import com.chika.decagontest.models.CarOwner;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarOwnersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CarOwnerAdapter adapter;
    ProgressBar bar;
    List<CarOwner> carOwners;
    List<String> models = new ArrayList<>();
    List<String> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_display);
        //sets back navigation icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //initialize components
        init();
        //loads car owners list from the file
        getCarOwners();
    }

    private void init(){
        //initializes the car owner recycler View and the adapter
        recyclerView = findViewById(R.id.car_owner_recycler);
        adapter = new CarOwnerAdapter(this, new ArrayList<>());
        //sets the recycler View manager and adapter
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        bar = findViewById(R.id.progress);
        bar.setVisibility(View.GONE);
    }

    private void getCarOwners(){
        runOnUiThread(() ->{
            try {
                //shows progress bar
                bar.setVisibility(View.VISIBLE);

                InputStream in = getResources().openRawResource(R.raw.car_ownsers_data);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
                carOwners = new ArrayList<>();
                String line = ""; int count = 0;
                while((line = reader.readLine()) != null){
                    //split
                    String[] token = line.split(",");
                    if(count > 0){
                        CarOwner carOwner = new CarOwner();
                        carOwner.setFirst_name(token[1]);
                        carOwner.setLast_name(token[2]);
                        carOwner.setEmail(token[3]);
                        carOwner.setCountry(token[4]);
                        carOwner.setCar_model(token[5]);
                        carOwner.setCar_model_year(token[6]);
                        carOwner.setCar_color(token[7]);
                        carOwner.setGender(token[8]);
                        carOwner.setJob_title(token[9]);
                        carOwner.setBio(token[10]);
                        //add to the list of car owners
                        carOwners.add(carOwner);
                    }
                    //inrement count
                    count += 1;
                }
                //display on the recycler view
                if(carOwners.size() > 0){
                    adapter = new CarOwnerAdapter(this, carOwners);
                    recyclerView.setAdapter(adapter);
                    //get models and country in the list
                    analyzeData();
                }
                //hides progress bar
                bar.setVisibility(View.GONE);
            }
            catch (Exception e){
                bar.setVisibility(View.GONE);
                Log.d("file_error", e.getMessage());
                //tell the user that file does not exist
                Toast.makeText(getBaseContext(), "Car Owners data not found.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.car_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.filter){
            //show filter panel as bottom sheet
            showFilter();
        }
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilter(){
        //show filter panel as bottom sheet dialog
        BottomSheetDialog sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setCanceledOnTouchOutside(true);
        sheetDialog.setCancelable(true);
        sheetDialog.setContentView(R.layout.filter_input_layout);

        //initialises the widgets on filter panel
        AutoCompleteTextView model_filter = sheetDialog.findViewById(R.id.filter_model);
        ArrayAdapter<String> model_adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, models);
        model_filter.setAdapter(model_adapter);

        AutoCompleteTextView country_filter = sheetDialog.findViewById(R.id.filter_country);
        ArrayAdapter<String> country_adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        country_filter.setAdapter(country_adapter);

        MaterialButton btn_c = sheetDialog.findViewById(R.id.btn_filter);
        //collects filter data on button click
        btn_c.setOnClickListener(v -> {
            String m = model_filter.getText().toString();
            String c = country_filter.getText().toString();
            if(!TextUtils.isEmpty(m) && !TextUtils.isEmpty(c)){
                //filters the result using model and country of the owner
                sheetDialog.dismiss();
                //filter
                List<CarOwner> carOwnerList = new ArrayList<>();
                for (CarOwner co : carOwners) {
                    if (co.getCountry().equals(c) && co.getCar_model().equals(m)) {
                        carOwnerList.add(co);
                    }
                }
                //now filter
                filterNow(carOwnerList);
            }
            else if(TextUtils.isEmpty(m) && !TextUtils.isEmpty(c)){
                //filters the result using the owners country
                sheetDialog.dismiss();
                List<CarOwner> carOwnerList = new ArrayList<>();
                for (CarOwner co : carOwners) {
                    if (co.getCountry().equals(c)) {
                        carOwnerList.add(co);
                    }
                }
                //continue filter
                filterNow(carOwnerList);
            }
            else if(!TextUtils.isEmpty(m) && TextUtils.isEmpty(c)){
                //filter the result using the car model
                sheetDialog.dismiss();
                List<CarOwner> carOwnerList = new ArrayList<>();
                for (CarOwner co : carOwners) {
                    if (co.getCar_model().equals(m)) {
                        carOwnerList.add(co);
                    }
                }
                //continue filter
                filterNow(carOwnerList);
            }
        });
        //shows dialog
        sheetDialog.show();
    }

    private void analyzeData(){
        for (CarOwner carOwner : carOwners){
            if(!models.contains(carOwner.getCar_model()))
                models.add(carOwner.getCar_model());

            if(!countries.contains(carOwner.getCountry()))
                countries.add(carOwner.getCountry());
        }
    }

    private void filterNow(List<CarOwner> owners){
        if(owners.size() > 0) {
            adapter = new CarOwnerAdapter(this, owners);
            recyclerView.setAdapter(adapter);
        }
        else
            Toast.makeText(getBaseContext(), "no result", Toast.LENGTH_SHORT).show();
    }


}