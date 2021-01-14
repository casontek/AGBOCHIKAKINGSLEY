package com.chika.decagontest.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chika.decagontest.R;
import com.chika.decagontest.activities.UserActivity;
import com.chika.decagontest.models.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    List<User> users;
    Context context;

    public UserAdapter(Context c, List<User> userList) {
        this.context = c;
        this.users = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_inflator, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        //mix the backgound color of items
        if (position%2 == 0)
            holder.conainer.setBackgroundColor(context.getResources().getColor(R.color.white));
        else
            holder.conainer.setBackgroundColor(context.getResources().getColor(R.color.grayish));
        //display users detail
        holder.view_name.setText(users.get(position).getFullName());
        holder.view_date.setText(users.get(position).getCreatedAt());

        if(users.get(position).getAvatar() != null)
            Glide.with(context).load(users.get(position).getAvatar())
                .into(holder.avater);

        //listens to item clicks
        holder.itemView.setOnClickListener(v -> {
            //open users detail activity
            User user = users.get(position);
            userDetail(user);
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder{
        View conainer;
        CircleImageView avater;
        TextView view_name, view_date;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            view_name = itemView.findViewById(R.id.user_name);
            view_date = itemView.findViewById(R.id.user_date);
            avater = itemView.findViewById(R.id.user_avater);
            conainer = itemView.findViewById(R.id.container);
        }
    }

    private void userDetail(User u){
        Intent i = new Intent(context, UserActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("names", u.getFullName());
        i.putExtra("colors", u.getColors());
        i.putExtra("countries", u.getCountries());
        i.putExtra("avater", u.getAvatar());
        i.putExtra("createdAt", u.getCreatedAt());
        i.putExtra("gender", u.getGender());
        context.startActivity(i);
    }
}
