package com.example.proyectoapp.Jugadores;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Jugador {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String pais;
    public boolean tarjetaAmarilla;
    public boolean tarjetaRoja;
    public int gol;
    public boolean cambio;
    public boolean onceInicial;
    public String equipo;

    public Jugador(String nombre, String pais, boolean tarjetaAmarilla, boolean tarjetaRoja, int gol, boolean cambio, boolean onceInicial, String equipo) {
        this.nombre = nombre;
        this.pais = pais;
        this.tarjetaAmarilla = tarjetaAmarilla;
        this.tarjetaRoja = tarjetaRoja;
        this.gol = gol;
        this.cambio = cambio;
        this.onceInicial = onceInicial;
        this.equipo = equipo;
    }
}
