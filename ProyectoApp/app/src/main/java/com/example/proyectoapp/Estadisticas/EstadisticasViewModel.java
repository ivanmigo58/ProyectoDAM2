package com.example.proyectoapp.Estadisticas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosManager;
import com.example.proyectoapp.Utils;

import java.util.List;

public class EstadisticasViewModel extends AndroidViewModel {

    private EstadisticasManager estadisticasManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoEstadisticaResult = new MutableLiveData<>();

    public EstadisticasViewModel(@NonNull Application application) {
        super(application);
        estadisticasManager = new EstadisticasManager(application);
    }

    // Insertar datos estadistica
    public void insertarDatosEstadistica(Estadisticas estadisticas) {
        estadisticasManager.insertarDatosEstadisticas(estadisticas, new EstadisticasManager.InsertarDatosCallback() {
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

    // Obtiene una lista con las estadisticas
    public LiveData<List<Estadisticas>> obtenerEstadisticas() {
        return estadisticasManager.obtenerEstadisticas();
    }

    // Eliminar partido
    public void eliminarEstadistica(Estadisticas estadisticas) {
        estadisticasManager.eliminarEstadistica(estadisticas, new EstadisticasManager.EliminarEstadisticasCallback() {
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

    // Modificar datos de un partido
    public void modificarEstadistica(Estadisticas estadisticas) {
        estadisticasManager.modificarEstadistica(estadisticas, new EstadisticasManager.ModificarEstadisticasCallback() {
            @Override
            public void modificadoOk() {
                modificandoEstadisticaResult.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void modificadoError() {
                modificandoEstadisticaResult.postValue(Utils.Valor.FALSE);
            }
        });
    }

}
