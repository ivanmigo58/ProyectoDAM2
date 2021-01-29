package com.example.proyectoapp.Clasificacion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GruposChampions {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String letraGrupo;


    public GruposChampions(String letraGrupo) {
        this.letraGrupo = letraGrupo;
    }
}
