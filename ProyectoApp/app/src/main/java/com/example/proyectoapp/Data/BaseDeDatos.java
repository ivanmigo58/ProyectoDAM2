package com.example.proyectoapp.Data;https://developer.android.com/training/data-storage/room#java

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.example.proyectoapp.Login.User;

import java.util.List;

@Database(entities = {User.class}, version = 1, exportSchema = false)
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
