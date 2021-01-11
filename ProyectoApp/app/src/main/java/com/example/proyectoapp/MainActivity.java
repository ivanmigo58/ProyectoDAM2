package com.example.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.databinding.ActivityMainBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        setSupportActionBar(binding.toolbar);

        // Los fragments que meto aqui, saldra el boton de hamburguesa
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.tabbed_graph,R.id.configuracionFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

    }




}
