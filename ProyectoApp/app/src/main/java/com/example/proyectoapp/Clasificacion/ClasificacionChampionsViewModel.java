package com.example.proyectoapp.Clasificacion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Jugadores.JugadorManager;
import com.example.proyectoapp.Utils;

import java.util.List;

public class ClasificacionChampionsViewModel extends AndroidViewModel {
    private ClasificacionChampionsManager clasificacionChampionsManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();


    public ClasificacionChampionsViewModel(Application application) {
        super(application);
        clasificacionChampionsManager = new ClasificacionChampionsManager(application);
    }

    // Insertar competiciones
    public void insertarDatosClasificacionChampions(ClasificacionChampions clasificacionChampions) {
        clasificacionChampionsManager.insertarDatosClasificacionChampions(clasificacionChampions, new ClasificacionChampionsManager.InsertarDatosCallback() {
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


    public LiveData<List<ClasificacionChampions>> obtenerEquiposClasificacion() {
        return clasificacionChampionsManager.obtenerEquiposChampions();
    }

    public void eliminarEquiposChampions() {
        clasificacionChampionsManager.eliminarEquipoChampions(new ClasificacionChampionsManager.EliminarCallback() {
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
