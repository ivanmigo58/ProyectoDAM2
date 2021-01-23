package com.example.proyectoapp.Jugadores;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesManager;
import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JugadorManager {
    private DaoBaseDeDatos daoBaseDeDatos;
    private Executor executor = Executors.newSingleThreadExecutor();

    interface InsertarDatosCallback {
        void insertOk();
        void insertError();
    }

    public interface EliminarJugadorCallback {
        void eliminadoOk();
        void eliminadoError();
    }


    JugadorManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    // Insertar datos de jugador
    void insertarDatosJugadores(Jugador jugador, InsertarDatosCallback insertarDatosCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertarJugadores(jugador);
                // Compruebo si se ha insertado
                if (daoBaseDeDatos.comprobarJugador(jugador.nombre, jugador.pais, jugador.tarjetaAmarilla, jugador.tarjetaRoja, jugador.gol, jugador.cambio, jugador.onceInicial) != null) {
                    insertarDatosCallback.insertOk();
                }
                // No existe la competicion insertado
                else {
                    insertarDatosCallback.insertError();
                }
            }
        });
    }

    // Devuelve una lista de un equipo
    LiveData<List<Jugador>> obtenerJugadores(String equipo, boolean onceInicial) {
        return daoBaseDeDatos.obtenerJugadoresEquipo(equipo, onceInicial);
    }

    // Eliminar jugadores
    void eliminarJugadores(EliminarJugadorCallback eliminarJugadorCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.eliminarJugadores();

                    eliminarJugadorCallback.eliminadoOk();

                    eliminarJugadorCallback.eliminadoError();
            }
        });
    }
}
