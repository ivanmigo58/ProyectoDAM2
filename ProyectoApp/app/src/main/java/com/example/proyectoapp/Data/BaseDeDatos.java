package com.example.proyectoapp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectoapp.Clasificacion.ClasificacionChampions;
import com.example.proyectoapp.Clasificacion.GruposChampions;
import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Equipo.Equipo;
import com.example.proyectoapp.Estadisticas.Estadisticas;
import com.example.proyectoapp.Jugadores.Jugador;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Login.User;

@Database(entities = {User.class, Partido.class, Competicion.class, Estadisticas.class, Jugador.class, Equipo.class, ClasificacionChampions.class, GruposChampions.class}, version = 18, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase{
    private static volatile BaseDeDatos INSTANCIA;

    public abstract DaoBaseDeDatos daoBaseDeDatos();


    public static BaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            BaseDeDatos.class, "futmundo.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

}
