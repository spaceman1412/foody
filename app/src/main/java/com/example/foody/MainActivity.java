package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    OrderFragment orderFragment = new OrderFragment();
    NotifyFragment notifyFragment = new NotifyFragment();
    UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.page_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
                        return true;
                    case R.id.page_orders:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,orderFragment).commit();
                        return true;
                    case R.id.page_notify:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,notifyFragment).commit();
                        return true;
                    case R.id.page_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,userFragment).commit();
                        return true;
                }
                return false;
            }
        });



    }
}