package com.example.rentify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class SignUpActivity extends AppCompatActivity {

    TextView login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.signUp_btn);
        login = findViewById(R.id.log_in);

//        login section
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        signup handler
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //redirect to login page after signing up
                // maybe show toast message as well display error as well after consuming APIs
//                Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
            }
        });
    }
}