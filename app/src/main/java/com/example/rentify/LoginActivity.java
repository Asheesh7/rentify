package com.example.rentify;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailSignIn , passwordSignIn;
    Button signInButton;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailSignIn = findViewById(R.id.emailAddress);
        passwordSignIn = findViewById(R.id.Password);
        signInButton = findViewById(R.id.loginBtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId , password , confirmPasswordSignUp;
                emailId = emailSignIn.getText().toString();
                password = passwordSignIn.getText().toString();


                if(emailId.isEmpty())
                {
                    emailSignIn.setError("Enter email");
                }
                if(password.isEmpty())
                {
                    passwordSignIn.setError("Enter password");
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(emailId , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser user = mAuth.getCurrentUser();
                                sendUserNextActivity(String.valueOf(user));
                                Toast.makeText(LoginActivity.this, "Sign Up is successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }

        });
    }

    public void signInClicked(View view) {
        // Start the Sign Up activity
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void sendUserNextActivity(String user) {
        // Start the Sign Up activity
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("USERNAME", user);
        startActivity(intent);
    }
}