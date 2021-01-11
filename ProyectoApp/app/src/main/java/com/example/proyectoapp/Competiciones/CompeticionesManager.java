package com.example.proyectoapp.Competiciones;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.Partidos.PartidosManager;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompeticionesManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    public interface EliminarCompeticionCallback {
        void eliminadoOk();
        void eliminadoError();
    }

    interface ModificarCompeticionesCallback {
        void modificadoOk();
        void modificadoError();
    }

    CompeticionesManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos de competicion
    void insertarDatosCompeticiones(Competiciones competiciones, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarCompeticiones(competiciones);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarCompeticion(competiciones.nombre) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe la competicion insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelven una lista de todas las comepticiones
    LiveData<List<Competiciones>> obtenerCompeticiones() {
        return daoBaseDeDatos.obtenerCompeticiones();
    }

    // Elimina una competicion
    void eliminarCompeticion(Competiciones competiciones, EliminarCompeticionCallback eliminarCompeticionCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarCompeticion(competiciones);
                // Compruebo si se ha eliminado el partido
                if (daoBaseDeDatos.comprobarCompeticion(competiciones.nombre) == null) {
                    eliminarCompeticionCallback.eliminadoOk();
                } else {
                    eliminarCompeticionCallback.eliminadoError();
                }
            }
        });
    }

    // Modificar los datos de una competicion
    void modificarCompeticion(Competiciones competiciones, ModificarCompeticionesCallback modificarCompeticionesCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.modificarCompeticion(competiciones);
                // Compruebo si existe, una vez modificado
                if (daoBaseDeDatos.comprobarCompeticion(competiciones.nombre) != null) {
                    modificarCompeticionesCallback.modificadoOk();
                } else {
                    modificarCompeticionesCallback.modificadoError();
                }
            }
        });
    }
}
