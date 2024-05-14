package com.example.rentify;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePageAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Room> roomList;
    private List<Room> filteredRoomList;
    public HomePageAdapter(Activity mContext, List<Room> roomList) {
        super(mContext, R.layout.list_item, roomList);
        this.mContext = mContext;
        this.roomList = roomList;
        this.filteredRoomList = new ArrayList<>(roomList); // Initialize filtered list with all items
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate the layout if convertView is null
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        // Get views
        TextView textName = convertView.findViewById(R.id.textName);
        TextView location = convertView.findViewById(R.id.location);
        TextView price = convertView.findViewById(R.id.price);
        TextView status = convertView.findViewById(R.id.status);
        ImageView imageView = convertView.findViewById(R.id.image);

        // Get room object
        Room room = filteredRoomList.get(position);

        // Set data to views
        location.setText(room.getLocation());
        textName.setText(room.getName());
        price.setText(String.valueOf(room.getPrice()));
        status.setText(room.getStatus());

        // Load image using Picasso
        Picasso.get()
                .load(room.getImage())
                .placeholder(R.drawable.rooms) // Placeholder image
                .error(R.drawable.rooms) // Error image
                .into(imageView);

        return convertView;
    }

    public void filter(String query) {
        filteredRoomList.clear();
        if (query.isEmpty()) {
            filteredRoomList.addAll(roomList); // If query is empty, show all items
        } else {
            query = query.toLowerCase(Locale.getDefault());
            for (Room room : roomList) {
                if (room.getName().toLowerCase(Locale.getDefault()).contains(query)) {
                    filteredRoomList.add(room); // Add items that match the query
                }
            }
        }
        notifyDataSetChanged(); // Notify adapter that data has changed
    }
}
