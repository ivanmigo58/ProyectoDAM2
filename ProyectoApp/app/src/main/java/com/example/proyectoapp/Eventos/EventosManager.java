package com.example.proyectoapp.Eventos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventosManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    public interface EliminarPartidoCallback {
        void eliminadoOk();
        void eliminadoError();
    }

    interface ModificarPartidosCallback {
        void modificadoOk();
        void modificadoError();
    }

    interface ModificarNoticiasCallback {
        void modificadoOk();
        void modificadoError();
    }

    EventosManager(Application application) {
           daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos
    void insertarDatos(Partidos partidos, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarPartidos(partidos);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarPartido(partidos.equipoLocal, partidos.equipoVisitante, partidos.horaInicio) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe el partido insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelve una lista de todos los reportes
    LiveData<List<Partidos>> obtenerPartidos(String equipoLocal, String equipoVisitante, String horaInicio) {
        return daoBaseDeDatos.obtenerPartidos(equipoLocal, equipoVisitante, horaInicio);
    }

    // Elimina un partido
}
