package com.example.proyectoapp.Eventos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Partidos {
    @PrimaryKey(autoGenerate = true)
    public String horaInicio;
    public String equipoLocal;
    public String equipoVisitante;

    public Partidos(String horaInicio, String equipoLocal, String equipoVisitante) {
        this.horaInicio = horaInicio;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }
}
