package com.example.proyectoapp.Partidos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;
import com.example.proyectoapp.Competiciones.Competiciones;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PartidosManager {
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

    public interface ModificarPartidosCallback {
        void modificadoOk();
        void modificadoError();
    }



    PartidosManager(Application application) {
           daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos de partido
    void insertarDatosPartidos(Partidos partidos, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarPartidos(partidos);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarPartido(partidos.equipoLocal, partidos.equipoVisitante, partidos.horaInicio, partidos.minPartido, partidos.resultadoLocal, partidos.resultadoVisitante) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe el partido insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }


    // Devuelve una lista de todos los partidos
    LiveData<List<Partidos>> obtenerPartidos() {
        return daoBaseDeDatos.obtenerPartidos();
    }


    // Elimina un partido
    void eliminarPartido(Partidos partidos, EliminarPartidoCallback eliminarPartidoCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarPartido(partidos);
                // Compruebo si se ha eliminado el partido
                if (daoBaseDeDatos.comprobarPartido(partidos.equipoLocal, partidos.equipoVisitante, partidos.horaInicio, partidos.minPartido, partidos.resultadoLocal, partidos.resultadoVisitante) == null) {
                    eliminarPartidoCallback.eliminadoOk();
                } else {
                    eliminarPartidoCallback.eliminadoError();
                }
            }
        });
    }


    // Modificar los datos del partido
    void modificarPartidos(Partidos partidos, ModificarPartidosCallback modificarPartidosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.modificarPartido(partidos);
                // Compruebo si existe, una vez modificado
                if (daoBaseDeDatos.comprobarPartido(partidos.equipoLocal, partidos.equipoVisitante, partidos.horaInicio, partidos.minPartido, partidos.resultadoLocal, partidos.resultadoVisitante) != null) {
                    modificarPartidosCallback.modificadoOk();
                } else {
                    modificarPartidosCallback.modificadoError();
                }
            }
        });
    }


}
