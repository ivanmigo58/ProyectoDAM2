package com.example.proyectoapp.Dialog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Dialog.DialogConfirmar.DataDialogConfirmar;
import com.example.proyectoapp.Dialog.DialogModificar.DataDialogModificar;

public class DialogViewModel extends AndroidViewModel {

    // Variable que contiene los datos del dialogo modificar
    public MutableLiveData<DataDialogModificar> dataDialogModificar = new MutableLiveData<>();
    public MutableLiveData<DataDialogConfirmar> dataDialogConfirmar = new MutableLiveData<>();

    public DialogViewModel(@NonNull Application application) {
        super(application);
    }
}

