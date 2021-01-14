package com.chika.decagontest.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chika.decagontest.R;
import com.chika.decagontest.activities.DetailActivity;
import com.chika.decagontest.activities.UserActivity;
import com.chika.decagontest.models.CarOwner;

import java.util.List;

public class CarOwnerAdapter extends RecyclerView.Adapter<CarOwnerAdapter.CarOHolder> {
    List<CarOwner> carOwners;
    Context context;

    public CarOwnerAdapter(Context c, List<CarOwner> owners) {
        this.context = c;
        this.carOwners = owners;
    }

    @NonNull
    @Override
    public CarOHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_owner_inflator, parent, false);
        return new CarOHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarOHolder holder, int position) {
        CarOwner carOwner = carOwners.get(position);
        //display owners info
        String names =carOwner.getFirst_name() + " " + carOwner.getLast_name();
        String car = "Car: " + carOwner.getCar_model() + ", " + carOwner.getCar_model_year();
        holder.tv_names.setText(names);
        holder.tv_email.setText(carOwner.getEmail());
        holder.tv_country.setText(carOwner.getCountry());
        holder.tv_car.setText(car);
        //view the detail on item click
        holder.itemView.setOnClickListener(v ->openDetail(carOwner));
    }

    @Override
    public int getItemCount() {
        return carOwners.size();
    }

    public class CarOHolder extends RecyclerView.ViewHolder {
        TextView tv_names, tv_email, tv_car, tv_country;

        public CarOHolder(@NonNull View itemView) {
            super(itemView);
            tv_names = itemView.findViewById(R.id.owner_names);
            tv_email = itemView.findViewById(R.id.owner_email);
            tv_country = itemView.findViewById(R.id.owner_country);
            tv_car = itemView.findViewById(R.id.owner_car);
        }
    }

    private void openDetail(CarOwner carOwner){
        Intent i = new Intent(context, DetailActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("fname", carOwner.getFirst_name());
        i.putExtra("lname", carOwner.getLast_name());
        i.putExtra("email", carOwner.getEmail());
        i.putExtra("country", carOwner.getCountry());
        i.putExtra("gender", carOwner.getGender());
        i.putExtra("job", carOwner.getJob_title());
        i.putExtra("bio", carOwner.getBio());
        i.putExtra("car_color", carOwner.getCar_color());
        i.putExtra("car_model", carOwner.getCar_model());
        i.putExtra("car_model_year", carOwner.getCar_model_year());
        context.startActivity(i);
    }
}
