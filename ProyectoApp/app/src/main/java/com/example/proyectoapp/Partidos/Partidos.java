package com.example.proyectoapp.Partidos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Partidos {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String horaInicio;
    public String equipoLocal;
    public String equipoVisitante;
    public String minPartido;
    public String resultadoLocal;
    public String resultadoVisitante;

    public Partidos(String horaInicio, String equipoLocal, String equipoVisitante, String minPartido, String resultadoLocal, String resultadoVisitante) {
        this.horaInicio = horaInicio;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.minPartido = minPartido;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;


    }
}
