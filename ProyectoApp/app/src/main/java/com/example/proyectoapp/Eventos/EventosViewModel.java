package com.example.proyectoapp.Eventos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Utils;

public class EventosViewModel extends AndroidViewModel {

    private EventosManager eventosManager;

    MutableLiveData<Utils.Valor> insertResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminandoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoPartidoResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> modificandoNoticiasResult = new MutableLiveData<>();



    public EventosViewModel(@NonNull Application application) {
        super(application);
        eventosManager = new EventosManager(application);
    }
}
