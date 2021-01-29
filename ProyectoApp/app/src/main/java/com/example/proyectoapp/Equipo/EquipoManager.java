package com.example.proyectoapp.Equipo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.Jugadores.Jugador;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EquipoManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }


    EquipoManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos equipo
    void insertarDatosEquipo (Equipo equipo, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarEquipo(equipo);
                if (daoBaseDeDatos.comprobarEquipo(equipo.nombre, equipo.paisClub, equipo.entrenador, equipo.fechaFundacion) != null) {
                    insertarDatosCallback.insertOk();
                }
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelve una lista de un equipo
    LiveData<List<Equipo>> obtenerEquipo(String nombre) {
        return daoBaseDeDatos.obtenerEquipo(nombre);
    }
}
