package com.example.proyectoapp.Estadisticas;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.Partidos.Partido;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EstadisticasManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    public interface EliminarEstadisticasCallback {
        void eliminadoOk();
        void eliminadoError();
    }

    public interface ModificarEstadisticasCallback {
        void modificadoOk();
        void modificadoError();
    }

    EstadisticasManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos de estadistica
    void insertarDatosEstadisticas(Estadisticas estadisticas, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarEstadisticas(estadisticas);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarEstadisticas(estadisticas.porcentajeLocal, estadisticas.porcentajeLocal, estadisticas.tituloEstadistica) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe la estadistica insertada
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelve una lista de todas las estadisticas
    LiveData<List<Estadisticas>> obtenerEstadisticas() {
        return daoBaseDeDatos.obtenerEstadisticas();
    }


    // Eliminar estadistica
    void eliminarEstadistica(Estadisticas estadisticas, EliminarEstadisticasCallback eliminarEstadisticasCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarEstadisticas(estadisticas);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarEstadisticas(estadisticas.porcentajeLocal, estadisticas.porcentajeLocal, estadisticas.tituloEstadistica) != null) {
                    eliminarEstadisticasCallback.eliminadoOk();
                }
                // No existe la estadistica insertada
                else {
                    eliminarEstadisticasCallback.eliminadoError();
                }
            }
        });
    }


    // Modificar estadistica
    void modificarEstadistica(Estadisticas estadisticas, ModificarEstadisticasCallback modificarEstadisticasCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.modificarEstadisticas(estadisticas);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarEstadisticas(estadisticas.porcentajeLocal, estadisticas.porcentajeLocal, estadisticas.tituloEstadistica) != null) {
                    modificarEstadisticasCallback.modificadoOk();
                }
                // No existe la estadistica insertada
                else {
                    modificarEstadisticasCallback.modificadoError();
                }
            }
        });
    }
}
