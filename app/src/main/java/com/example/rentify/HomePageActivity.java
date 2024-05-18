package com.example.rentify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    ListView myHompageListView;
    List<Room> roomList;
    Room room;

    DatabaseReference roomdatabase;

    DatabaseReference userdatabase;
    DatabaseReference wishListdatabase;

    private DrawerLayout drawer;
    private NavigationView navView;
    public static final boolean IS_HOME_ACTIVITY = true;










    HomePageAdapter homePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String username = getIntent().getStringExtra("USERNAME");

        // Display username
        TextView textViewUsername = findViewById(R.id.username);
        textViewUsername.setText(username);


        myHompageListView = findViewById(R.id.myHompageListView);
        SearchView searchView = findViewById(R.id.searchView);

        roomList = new ArrayList<>();



        roomdatabase = FirebaseDatabase.getInstance().getReference("ROOM");

//        userdatabase = FirebaseDatabase.getInstance().getReference("USER");
//        Query query = userdatabase.orderByChild("email").equalTo(username);




        // Add ValueEventListener to fetch data from Firebase
        roomdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomList.clear();
                for (DataSnapshot roomDataSnap : snapshot.getChildren()) {
                    Room room = roomDataSnap.getValue(Room.class);
                    if (room != null) {
                        roomList.add(room);
                    } else {
                        Toast.makeText(HomePageActivity.this, "Failed to parse room data", Toast.LENGTH_SHORT).show();
                    }
                }
                // Update the ListView adapter with the filtered data
                HomePageAdapter homePageAdapter = new HomePageAdapter(HomePageActivity.this, roomList);
                myHompageListView.setAdapter(homePageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this, "Failed to fetch data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });








        // handling drawer toggle functionality
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
//        drawer.setScrimColor('4F4F4F');
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        handling extra text
//        TextView extraText = findViewById(R.id.username);
//        extraText.setText("Jane");
//        TextView textViewUsername = findViewById(R.id.username);
//        textViewUsername.setText(username);

         navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(menuItem -> {
            // Handle menu item selected
            menuItem.setChecked(true);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });



        //handling bottom navigation menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                showHome();
                return true;
            } else if (id == R.id.nav_explore) {
                showExplore();
                return true;
            } else if (id == R.id.nav_community) {
                showCommunity();
                return true;
            } else if (id == R.id.nav_wishlist) {
                showWishList();
            }
            return false;
        });

//        wishListdatabase = FirebaseDatabase.getInstance().getReference("WISHLIST");
//        btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inserDataToWishlist();
//            }
//        });
    }


    private void inserDataToWishlist() {

        roomdatabase.child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String roomName = snapshot.child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showHome() {
        if(!HomePageActivity.IS_HOME_ACTIVITY) {
            Intent intent = new Intent(HomePageActivity.this, HomePageActivity.class);
            startActivity(intent);
        }
    }
    private void showExplore() {
        Intent intent = new Intent(HomePageActivity.this, ExploreActivity.class);
        startActivity(intent);
    }
    private void showCommunity() {
        Intent intent = new Intent(HomePageActivity.this, CommunityActivity.class);
        startActivity(intent);
    }
    private void showWishList() {
        Intent intent = new Intent(HomePageActivity.this, WishlistActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

