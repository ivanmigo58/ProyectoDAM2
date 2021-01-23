package com.example.proyectoapp.Jugadores;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesManager;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosManager;
import com.example.proyectoapp.Utils;

import java.util.List;

public class JugadorViewModel extends AndroidViewModel {

    private JugadorManager jugadorManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();

    public JugadorViewModel(@NonNull Application application) {
        super(application);
        jugadorManager = new JugadorManager(application);
    }

    // Insertar competiciones
    public void insertarDatosJugador(Jugador jugador) {
        jugadorManager.insertarDatosJugadores(jugador, new JugadorManager.InsertarDatosCallback() {
            @Override
            public void insertOk() {
                insertResult.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void insertError() {
                insertResult.postValue(Utils.Valor.FALSE);
            }
        });
    }

    // Obtener una lista de los jugadores
    public LiveData<List<Jugador>> obtenerJugadores(String equipo, boolean onceInicial) {
        return jugadorManager.obtenerJugadores(equipo, onceInicial);
    }

    // Eliminar partido
    public void eliminarJugador() {
        jugadorManager.eliminarJugadores(new JugadorManager.EliminarJugadorCallback() {
            @Override
            public void eliminadoOk() {
                eliminandoResult.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void eliminadoError() {
                eliminandoResult.postValue(Utils.Valor.FALSE);
            }
        });
    }
}
