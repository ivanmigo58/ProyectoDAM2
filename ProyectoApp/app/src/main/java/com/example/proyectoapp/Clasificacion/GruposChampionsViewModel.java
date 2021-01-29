package com.example.proyectoapp.Clasificacion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesManager;
import com.example.proyectoapp.Utils;

import java.util.List;

public class GruposChampionsViewModel extends AndroidViewModel {

    private GruposChampionsManager gruposChampionsManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();

    public GruposChampionsViewModel(@NonNull Application application) {
        super(application);
        gruposChampionsManager = new GruposChampionsManager(application);
    }

    // Insertar competiciones
    public void insertarDatosGrupos(GruposChampions gruposChampions) {
        gruposChampionsManager.insertarDatosGrupos(gruposChampions, new GruposChampionsManager.InsertarDatosCallback() {
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


    // Obtener una lista con las competiciones
    public LiveData<List<GruposChampions>> obtenerGrupos() {
        return  gruposChampionsManager.obtenerGrupos();
    }
}
