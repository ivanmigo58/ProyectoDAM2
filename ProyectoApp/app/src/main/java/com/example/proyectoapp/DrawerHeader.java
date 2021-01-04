package com.example.proyectoapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DrawerHeader extends AppCompatActivity {
    com.example.proyectoapp.databinding.DrawerHeaderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_header);
    }
}