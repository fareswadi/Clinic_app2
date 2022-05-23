package com.example.clinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainUI extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    HomeFragment homeFragment = new HomeFragment();
    AppointmentFragment appointmentFragment = new AppointmentFragment();
    TipsFragment tipsFragment = new TipsFragment();
    PersonalFragment personalFragment = new PersonalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ft.replace(R.id.FCV, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FCV, homeFragment).commit();
                        break;
                    case R.id.appointment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FCV, appointmentFragment).commit();
                        break;
                    case R.id.disease:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FCV, tipsFragment).commit();
                        break;
                    case R.id.personal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FCV, personalFragment).commit();
                        break;
                }
                return true;
            }
        });

    }
//        private void checkUser(){
//            SharedPreferences sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
//            boolean isShared = sharedPreferences.getBoolean("isSharedPref", Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
//            String email = sharedPreferences.getString("userEmail", String.valueOf(MODE_PRIVATE));
//
//            if (isShared) {
//
//            } else {
//            }
//        }

}