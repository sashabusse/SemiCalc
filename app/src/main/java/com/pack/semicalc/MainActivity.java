package com.pack.semicalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        BottomNavigationView bottomNavView = findViewById(R.id.nav_bar);

        AppBarConfiguration barConfiguration = new AppBarConfiguration.Builder(
                R.id.bottom_bar_h_parameters,R.id.bottom_bar_cir_parameters).build();

        NavController navController = Navigation.findNavController(this, R.id.host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, barConfiguration);
        NavigationUI.setupWithNavController(bottomNavView, navController);


    }
}
