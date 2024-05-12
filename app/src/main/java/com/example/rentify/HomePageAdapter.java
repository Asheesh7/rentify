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

import java.util.List;

public class HomePageAdapter extends ArrayAdapter {
    private Activity mContext;
    List<Room> roomList;
    public HomePageAdapter(Activity mcontext , List<Room> roomList)
    {
        super(mcontext , R.layout.list_item , roomList);
        this.mContext = mcontext;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listHomePageList = inflater.inflate(R.layout.list_item,null,true);

        TextView textName = listHomePageList.findViewById(R.id.textName);
        TextView location = listHomePageList.findViewById(R.id.location);
        TextView price = listHomePageList.findViewById(R.id.price);
        TextView status = listHomePageList.findViewById(R.id.status);

        ImageView imageView = listHomePageList.findViewById(R.id.image);





        Room room = roomList.get(position);


        location.setText(room.getLocation());
        textName.setText(room.getName());
        price.setText(String.valueOf(room.getPrice()));
        status.setText(room.getStatus());
//        Picasso.get().load(students.getImage()).into(imageView);
        Picasso.get()
                .load(room.getImage())
                .placeholder(R.drawable.rooms) // Placeholder image
                .error(R.drawable.rooms) // Error image
                .into(imageView);

        return listHomePageList;

    }
}
