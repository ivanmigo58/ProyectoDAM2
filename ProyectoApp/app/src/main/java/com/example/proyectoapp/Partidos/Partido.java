package com.example.proyectoapp.Partidos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Partido {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String horaInicio;
    public String equipoLocal;
    public String equipoVisitante;
    public String minPartido;
    public String resultadoLocal;
    public String resultadoVisitante;
    public String competicion;
    public boolean enVivo;
    public String fecha;

    public Partido(String horaInicio, String equipoLocal, String equipoVisitante, String minPartido, String resultadoLocal, String resultadoVisitante, String competicion, boolean enVivo, String fecha) {
        this.horaInicio = horaInicio;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.minPartido = minPartido;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
        this.competicion = competicion;
        this.enVivo = enVivo;
        this.fecha = fecha;
    }
}
