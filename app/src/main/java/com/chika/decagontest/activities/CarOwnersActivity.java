package com.chika.decagontest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.chika.decagontest.R;
import com.chika.decagontest.adapters.CarOwnerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CarOwnersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CarOwnerAdapter adapter;
    ProgressBar bar;

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
        AutoCompleteTextView country_filter = sheetDialog.findViewById(R.id.filter_country);
        MaterialButton btn_c = sheetDialog.findViewById(R.id.btn_filter);
        //collects filter data on button click
        btn_c.setOnClickListener(v -> {
            String m = model_filter.getText().toString();
            String c = country_filter.getText().toString();
            if(!TextUtils.isEmpty(m) && !TextUtils.isEmpty(c)){
                //filters the result using model and country of the owner
                sheetDialog.dismiss();
            }
            else if(TextUtils.isEmpty(m) && !TextUtils.isEmpty(c)){
                //filters the result using the owners country
                sheetDialog.dismiss();
            }
            else if(!TextUtils.isEmpty(m) && TextUtils.isEmpty(c)){
                //filter the result using the car model
                sheetDialog.dismiss();
            }
        });
        //shows dialog
        sheetDialog.show();
    }

}