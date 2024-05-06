package com.example.rentify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, pswrd;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.emailAddress);
        pswrd = findViewById(R.id.Password);
        signInBtn = findViewById(R.id.loginBtn);
        TextView forgotPswrd = findViewById(R.id.forgotPassword);
        TextView signUp = findViewById(R.id.signUp);

//        Sign in functionality to be added
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
            }
        });

        //    Forgot password section
        forgotPswrd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent to start the ForgotPasswordActivity
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

//        signup section
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent to start the signUpActivity
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }


}