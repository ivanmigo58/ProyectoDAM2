package com.example.proyectoapp.Competiciones;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Utils;

import java.util.List;

public class CompeticionesViewModel extends AndroidViewModel {

    private CompeticionesManager competicionesManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoCompeticionResult = new MutableLiveData<>();

    public CompeticionesViewModel(Application application) {
        super(application);
        competicionesManager = new CompeticionesManager(application);
    }

    // Insertar competiciones
    public void insertarDatosCompeticiones(Competiciones competiciones) {
        competicionesManager.insertarDatosCompeticiones(competiciones, new CompeticionesManager.InsertarDatosCallback() {
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
    public LiveData<List<Competiciones>> obtenerCompeticiones() {
        return  competicionesManager.obtenerCompeticiones();
    }

    // Eliminar competicion
    public void eliminarCompeticion(Competiciones competiciones) {
        competicionesManager.eliminarCompeticion(competiciones, new CompeticionesManager.EliminarCompeticionCallback() {
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

    // Modificar datos de una competicion
    public void modificarCompeticion(Competiciones competiciones) {
        competicionesManager.modificarCompeticion(competiciones, new CompeticionesManager.ModificarCompeticionesCallback() {
            @Override
            public void modificadoOk() {
                modificandoCompeticionResult.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void modificadoError() {
                modificandoCompeticionResult.postValue(Utils.Valor.FALSE);
            }
        });
    }
}
