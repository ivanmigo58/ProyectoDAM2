package com.example.proyectoapp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proyectoapp.Competiciones.Competiciones;
import com.example.proyectoapp.Partidos.Partidos;
import com.example.proyectoapp.Login.User;

@Database(entities = {User.class, Partidos.class, Competiciones.class}, version = 5, exportSchema = false)
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
