package com.example.proyectoapp.Competiciones;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Competicion {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public Competicion(String nombre) {
        this.nombre = nombre;
    }
}
