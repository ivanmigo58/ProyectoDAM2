package com.example.proyectoapp.Competiciones;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

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
    void insertarDatosCompeticiones(Competicion competicion, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarCompeticiones(competicion);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarCompeticion(competicion.nombre) != null) {
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
    LiveData<List<Competicion>> obtenerCompeticiones() {
        return daoBaseDeDatos.obtenerCompeticiones();
    }

    // Elimina una competicion
    void eliminarCompeticion(Competicion competicion, EliminarCompeticionCallback eliminarCompeticionCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarCompeticion(competicion);
                // Compruebo si se ha eliminado el partido
                if (daoBaseDeDatos.comprobarCompeticion(competicion.nombre) == null) {
                    eliminarCompeticionCallback.eliminadoOk();
                } else {
                    eliminarCompeticionCallback.eliminadoError();
                }
            }
        });
    }

    // Modificar los datos de una competicion
    void modificarCompeticion(Competicion competicion, ModificarCompeticionesCallback modificarCompeticionesCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.modificarCompeticion(competicion);
                // Compruebo si existe, una vez modificado
                if (daoBaseDeDatos.comprobarCompeticion(competicion.nombre) != null) {
                    modificarCompeticionesCallback.modificadoOk();
                } else {
                    modificarCompeticionesCallback.modificadoError();
                }
            }
        });
    }
}
