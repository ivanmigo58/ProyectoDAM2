package com.example.proyectoapp.Partidos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

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
    void insertarDatosPartidos(Partido partido, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarPartidos(partido);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarPartido(partido.equipoLocal, partido.equipoVisitante, partido.horaInicio, partido.minPartido, partido.resultadoLocal, partido.resultadoVisitante, partido.competicion, partido.enVivo, partido.fecha) != null) {
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
    LiveData<List<Partido>> obtenerPartidos() {
        return daoBaseDeDatos.obtenerPartidos();
    }


    // Elimina un partido
    void eliminarPartido(Partido partido, EliminarPartidoCallback eliminarPartidoCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarPartido(partido);
                // Compruebo si se ha eliminado el partido
                if (daoBaseDeDatos.comprobarPartido(partido.equipoLocal, partido.equipoVisitante, partido.horaInicio, partido.minPartido, partido.resultadoLocal, partido.resultadoVisitante, partido.competicion, partido.enVivo, partido.fecha) == null) {
                    eliminarPartidoCallback.eliminadoOk();
                } else {
                    eliminarPartidoCallback.eliminadoError();
                }
            }
        });
    }


    // Modificar los datos del partido
    void modificarPartidos(Partido partido, ModificarPartidosCallback modificarPartidosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.modificarPartido(partido);
                // Compruebo si existe, una vez modificado
                if (daoBaseDeDatos.comprobarPartido(partido.equipoLocal, partido.equipoVisitante, partido.horaInicio, partido.minPartido, partido.resultadoLocal, partido.resultadoVisitante, partido.competicion, partido.enVivo, partido.fecha) != null) {
                    modificarPartidosCallback.modificadoOk();
                } else {
                    modificarPartidosCallback.modificadoError();
                }
            }
        });
    }


}
