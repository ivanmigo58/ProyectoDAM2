package com.example.proyectoapp.Partidos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Competiciones.Competiciones;
import com.example.proyectoapp.Partidos.Partidos;
import com.example.proyectoapp.Partidos.PartidosManager;
import com.example.proyectoapp.Utils;

import java.util.List;

public class PartidosViewModel extends AndroidViewModel {

    private PartidosManager partidosManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoPartidoResult = new MutableLiveData<>();



    public PartidosViewModel(Application application) {
        super(application);
        partidosManager = new PartidosManager(application);
    }

    // Insertar datos partido
    public void insertarDatosPartidos(Partidos partidos) {
        partidosManager.insertarDatosPartidos(partidos, new PartidosManager.InsertarDatosCallback() {
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



    // Obtiene una lista con los partidos
    public LiveData<List<Partidos>> obtenerPartidos() {
        return partidosManager.obtenerPartidos();
    }


    // Eliminar partido
    public void eliminarPartido(Partidos partidos) {
        partidosManager.eliminarPartido(partidos, new PartidosManager.EliminarPartidoCallback() {
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
    public void modificarPartido(Partidos partidos) {
        partidosManager.modificarPartidos(partidos, new PartidosManager.ModificarPartidosCallback() {
            @Override
            public void modificadoOk() {
                modificandoPartidoResult.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void modificadoError() {
                modificandoPartidoResult.postValue(Utils.Valor.FALSE);
            }
        });
    }

}
