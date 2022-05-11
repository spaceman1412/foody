package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import com.example.foody.model.SingletonLogin;

public class SpashScreen extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(() -> {
            if(SingletonLogin.isLogined())
            {
                intent = new Intent(this, MainActivity.class);
            }
            else intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
    }