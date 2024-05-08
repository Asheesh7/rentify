package com.example.rentify;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navView;
    public static final boolean IS_HOME_ACTIVITY = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

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
        TextView extraText = findViewById(R.id.username);
        extraText.setText("Jane");

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
        Intent intent = new Intent(HomePageActivity.this, WIshlistActivity.class);
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