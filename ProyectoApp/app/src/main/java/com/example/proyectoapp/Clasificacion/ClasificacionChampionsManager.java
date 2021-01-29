package com.example.proyectoapp.Clasificacion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesManager;
import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.Jugadores.JugadorManager;
import com.example.proyectoapp.Utils;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ClasificacionChampionsManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    public interface EliminarCallback {
        void eliminadoOk();
        void eliminadoError();
    }

    ClasificacionChampionsManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }


    void insertarDatosClasificacionChampions(ClasificacionChampions clasificacionChampions, ClasificacionChampionsManager.InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarEquiposChampions(clasificacionChampions);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarEquipoChampions(clasificacionChampions.posicion, clasificacionChampions.nombreEquipo, clasificacionChampions.partidosJugados, clasificacionChampions.diferenciaGoles, clasificacionChampions.puntos, clasificacionChampions.grupo) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe la competicion insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    LiveData<List<ClasificacionChampions>> obtenerEquiposChampions() {
        return daoBaseDeDatos.obtenerEquipoChampions();
    }


    void eliminarEquipoChampions(EliminarCallback eliminarCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarEquiposChampions();
                eliminarCallback.eliminadoOk();
            }
        });
    }

}
