package com.example.proyectoapp.Clasificacion;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesManager;
import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GruposChampionsManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    GruposChampionsManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos de grupos
    void insertarDatosGrupos(GruposChampions gruposChampions, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarGrupos(gruposChampions);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarGrupos(gruposChampions.letraGrupo) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe la competicion insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelven una lista de todos los grupos
    LiveData<List<GruposChampions>> obtenerGrupos() {
        return daoBaseDeDatos.obtenerGrupos();
    }
}
