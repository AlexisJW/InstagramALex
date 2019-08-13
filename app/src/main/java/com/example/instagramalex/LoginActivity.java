package com.example.instagramalex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.SignUpCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity {

    private static String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button bntLogin;
    private Button btnSignup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bntLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        progressBar = (ProgressBar) findViewById(R.id.pbLoading);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            goMainActivity();
        }

        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(VISIBLE);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "username or password is empty!!! please Insert them.", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    login(username, password);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(VISIBLE);
                signup();
            }
        });
    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
               if (e != null){
                   Log.e(TAG, "Issue with login");
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(LoginActivity.this, "incorrect password or username", Toast.LENGTH_LONG).show();
                   e.printStackTrace();
                   return;
               }
               // navigate to new activity if the user has signed properly
               goMainActivity();
            }
        });
    }

    private void signup(){
        ParseUser user = new ParseUser();
        // Set the user's username and password, which can be obtained by a forms
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this, "username or password is empty!!! please Insert them.", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }else {
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null){
                        Log.e(TAG, "Issue with Signup");
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "this username is already used", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                        return;
                    }else{
                        goMainActivity();
                    }
                }
            });
        }

    }

    private void goMainActivity() {
        Log.d(TAG, "login1");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
