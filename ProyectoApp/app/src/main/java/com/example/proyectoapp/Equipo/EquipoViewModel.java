package com.example.proyectoapp.Equipo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Jugadores.Jugador;
import com.example.proyectoapp.Jugadores.JugadorManager;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Utils;

import java.util.List;

public class EquipoViewModel extends AndroidViewModel {

    private EquipoManager equipoManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public  MutableLiveData<String> equipoSeleccionado = new MutableLiveData<>();
    public  MutableLiveData<Equipo> seleccionado = new MutableLiveData<>();

    public EquipoViewModel(@NonNull Application application) {
        super(application);
        equipoManager = new EquipoManager(application);
    }

    // Insertar equipo
    public void insertarDatosEquipo(Equipo equipo) {
        equipoManager.insertarDatosEquipo(equipo, new EquipoManager.InsertarDatosCallback() {
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

    // Mostrar pantalla del partido que selecciones
    public void seleccionar(Equipo equipo) {
        seleccionado.setValue(equipo);
    }
    public MutableLiveData<Equipo> seleccionadoEquipo(){
        return seleccionadoEquipo();
    }

    public void seleccionarEquipo(Equipo equipo) {
        equipoSeleccionado.setValue(equipo.nombre);
    }

    public MutableLiveData<String> equipoSeleccionado() {
        return equipoSeleccionado;
    }

    // Obtener una lista de los equipos
    public LiveData<List<Equipo>> obtenerEquipo(String nombre) {
        return equipoManager.obtenerEquipo(nombre);
    }

}
