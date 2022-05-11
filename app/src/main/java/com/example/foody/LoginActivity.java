package com.example.foody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foody.SQL.FoodyDbHelper;
import com.example.foody.model.SingletonLogin;
import com.example.foody.model.User;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    TextView signUp;
    AppCompatButton login;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        signUp = findViewById(R.id.textViewSignUp);
        login = findViewById(R.id.buttonLogin);
        FoodyDbHelper db = new FoodyDbHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((EditText) findViewById(R.id.inputEmail)).getText().toString().trim();
                password = ((EditText) findViewById(R.id.inputPassword)).getText().toString().trim();

                Boolean checkUser =  db.checkUser(email,password);

                if(checkUser)
                {
                    SingletonLogin.setLogined(true);
//                    SingletonLogin.setUserId(user.getUserId());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.linearLayout),"Wrong username or password",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });


    }
}