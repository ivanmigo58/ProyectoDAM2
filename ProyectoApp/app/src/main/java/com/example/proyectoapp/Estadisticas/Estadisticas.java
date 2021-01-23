package com.example.proyectoapp.Estadisticas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.proyectoapp.Partidos.Partido;

@Entity
public class Estadisticas {
    @PrimaryKey(autoGenerate = true)
    public int idPartido;
    public int porcentajeLocal;
    public int porcentajeVisitante;
    public String tituloEstadistica;

    public Estadisticas(int porcentajeLocal, int porcentajeVisitante, String tituloEstadistica) {
        this.porcentajeLocal = porcentajeLocal;
        this.porcentajeVisitante = porcentajeVisitante;
        this.tituloEstadistica = tituloEstadistica;
    }
}
