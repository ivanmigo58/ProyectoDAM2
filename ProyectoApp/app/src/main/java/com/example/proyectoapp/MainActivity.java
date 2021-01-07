package com.example.proyectoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.proyectoapp.Eventos.Noticias;
import com.example.proyectoapp.Eventos.Partidos;
import com.example.proyectoapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

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

        // Cuando la base de datos este vacia
        insertarDatosDefecto();


        // La DB no esta vacia, no hago nada

    }

    private void insertarDatosDefecto() {
        List<Noticias> listNoticias = new ArrayList<>();
        listNoticias.add(new Noticias("covid", "/src/image"));
        listNoticias.add(new Noticias("covid", "/src/image"));
        listNoticias.add(new Noticias("covid", "/src/image"));

        List<Partidos> listPartidos = new ArrayList<>();
        listPartidos.add(new Partidos("21:00","Real Madrid", "Inter"));
        listPartidos.add(new Partidos("18:55","Barcelona", "Dinamo Kiev"));
        listPartidos.add(new Partidos("21:00","Atalanta", "Liverpool"));

    }


}
