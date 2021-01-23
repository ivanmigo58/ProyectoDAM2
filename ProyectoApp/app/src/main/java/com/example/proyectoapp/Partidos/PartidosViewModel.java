package com.example.proyectoapp.Partidos;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Utils;

import java.util.List;
import java.util.jar.Attributes;

public class PartidosViewModel extends AndroidViewModel {

    private PartidosManager partidosManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoPartidoResult = new MutableLiveData<>();
    public  MutableLiveData<Partido> partidoSeleccionado = new MutableLiveData<>();
    public  MutableLiveData<String> equipoSeleccionado = new MutableLiveData<>();



    public PartidosViewModel(Application application) {
        super(application);
        partidosManager = new PartidosManager(application);
    }

    // Insertar datos partido
    public void insertarDatosPartidos(Partido partido) {
        partidosManager.insertarDatosPartidos(partido, new PartidosManager.InsertarDatosCallback() {
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
    public LiveData<List<Partido>> obtenerPartidos() {
        return partidosManager.obtenerPartidos();
    }


    // Mostrar pantalla del partido que selecciones
    public void seleccionar(Partido partido) {
        partidoSeleccionado.setValue(partido);
    }
    public MutableLiveData<Partido> seleccionado(){
        return partidoSeleccionado;
    }

    public void seleccionarEquipoLocal(Partido partido) {
        equipoSeleccionado.setValue(partido.equipoLocal);
    }

    public void seleccionarEquipoVisitante(Partido partido) {
        equipoSeleccionado.setValue(partido.equipoVisitante);
    }
    public MutableLiveData<String> equipoSeleccionado(){
        return equipoSeleccionado;
    }


    // Eliminar partido
    public void eliminarPartido(Partido partido) {
        partidosManager.eliminarPartido(partido, new PartidosManager.EliminarPartidoCallback() {
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
    public void modificarPartido(Partido partido) {
        partidosManager.modificarPartidos(partido, new PartidosManager.ModificarPartidosCallback() {
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
