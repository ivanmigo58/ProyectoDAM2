package com.example.proyectoapp.Equipo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equipo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String paisClub;
    public String entrenador;
    public String fechaFundacion;
    public int totalJugadores;
    public float edadMedia;
    public int jugadoresExtranjeros;
    public int jugadoresNacionales;
    public String torneo1;
    public String torneo2;
    public String torneo3;
    public String torneo4;

    public Equipo(String nombre, String paisClub, String entrenador, String fechaFundacion, int totalJugadores, float edadMedia, int jugadoresExtranjeros, int jugadoresNacionales, String torneo1, String torneo2, String torneo3, String torneo4) {
        this.nombre = nombre;
        this.paisClub = paisClub;
        this.entrenador = entrenador;
        this.fechaFundacion = fechaFundacion;
        this.totalJugadores = totalJugadores;
        this.edadMedia = edadMedia;
        this.jugadoresExtranjeros = jugadoresExtranjeros;
        this.jugadoresNacionales = jugadoresNacionales;
        this.torneo1 = torneo1;
        this.torneo2 = torneo2;
        this.torneo3 = torneo3;
        this.torneo4 = torneo4;
    }
}
