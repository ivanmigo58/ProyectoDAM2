package com.example.proyectoapp.Competiciones;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Competiciones {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public Competiciones(String nombre) {
        this.nombre = nombre;
    }
}
