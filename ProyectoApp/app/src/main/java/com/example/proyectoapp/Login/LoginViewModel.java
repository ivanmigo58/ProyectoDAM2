package com.example.proyectoapp.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private LoginManager loginManager;

    // Variables que observan el fragment
    MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    MutableLiveData<Boolean> registroResult = new MutableLiveData<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginManager = new LoginManager(application);
    }

    // Obtengo los datos
    void obtenerUser(String usuario, String password) {
        loginManager.validarUser(usuario, password, new LoginManager.LoginLlamadaVuelta() {
            @Override
            public void cueandoLoginOk() {
                loginResult.postValue(true);
            }

            @Override
            public void cuandoLoginError() {
                loginResult.postValue(false);
            }
        });
    }

    // Inserta los datos
    public void insertar(User user) {
        loginManager.insertar(user, new LoginManager.RegistroLlamadaVuelta() {
            @Override
            public void cuandoRegistroOk() {
                registroResult.postValue(true);
            }

            @Override
            public void cuandoUsuarioYaExiste() {
                registroResult.postValue(false);
            }
        });

    }
}


