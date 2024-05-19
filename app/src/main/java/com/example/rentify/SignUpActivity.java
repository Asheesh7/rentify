package com.example.rentify;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentify.LoginActivity;
import com.example.rentify.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {



    EditText emailSignUp , passwordSignUp , confirmPassword;
    Button signUpButtons;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ProgressDialog progressDialog;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        emailSignUp = findViewById(R.id.emailAddress);
        passwordSignUp = findViewById(R.id.Password);
        confirmPassword = findViewById(R.id.confirm_password);
        signUpButtons = findViewById(R.id.signUp_btn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signUpButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailId , password , confirmPasswordSignUp;
                emailId = emailSignUp.getText().toString();
                password = passwordSignUp.getText().toString();
                confirmPasswordSignUp = confirmPassword.getText().toString();

                if(emailId.isEmpty())
                {
                    emailSignUp.setError("Enter email");
                }
                if(password.isEmpty())
                {
                    passwordSignUp.setError("Enter password");
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(emailId , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                sendUserNextActivity();
                                Toast.makeText(SignUpActivity.this, "Sign Up is successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUpActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

    }

    public void sendUserNextActivity() {
        // Start the Sign Up activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}