package com.example.proyectoapp.Clasificacion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ClasificacionChampions {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int posicion;
    public String nombreEquipo;
    public int partidosJugados;
    public int diferenciaGoles;
    public int puntos;
    public String grupo;

    public ClasificacionChampions(int posicion, String nombreEquipo, int partidosJugados, int diferenciaGoles, int puntos, String grupo) {
        this.posicion = posicion;
        this.nombreEquipo = nombreEquipo;
        this.partidosJugados = partidosJugados;
        this.diferenciaGoles = diferenciaGoles;
        this.puntos = puntos;
        this.grupo = grupo;
    }
}
