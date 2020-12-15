package com.example.proyectoapp.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {

    private LoginManager loginManager;
    public MutableLiveData<User> userActual = new MutableLiveData<>();

    // Variables que observan el fragment
    MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    MutableLiveData<Boolean> registroResult = new MutableLiveData<>();
    public MutableLiveData<Boolean> updateUsuario = new MutableLiveData<>();
    public MutableLiveData<Boolean> updatePassword = new MutableLiveData<>();
    public MutableLiveData<Boolean> mostrarPassword = new MutableLiveData<>();



    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginManager = new LoginManager(application);
    }

    // Obtengo los datos
    void obtenerUser(String usuario, String password) {
        loginManager.validarUser(usuario, password, new LoginManager.LoginCallback() {
            @Override
            public void cueandoLoginOk(User user) {
                loginResult.postValue(true);
                userActual.postValue(user);
            }

            @Override
            public void cuandoLoginError() {
                loginResult.postValue(false);
            }
        });
    }


    // Inserta los datos
    public void insertar(User user) {
        loginManager.insertar(user, new LoginManager.RegistroCallback() {
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

    // Actualiza el usuario
    public void updateUsuario(User user, String nuevoUsuario) {
        loginManager.updateUsuario(user, nuevoUsuario, new LoginManager.UpdateUsuarioCallback() {
            @Override
            public void usuarioActualizadoOk() {
                updateUsuario.postValue(true);
                userActual.postValue(user);
            }

            @Override
            public void usuarioError() {
                updateUsuario.postValue(false);
            }
        });
    }

    // Actualiza la contraseña
    public void updatePassword(User user) {
        loginManager.updatePassword(user, new LoginManager.UpdatePasswordCallback() {
            @Override
            public void passwordActualziadoOk() {
                updatePassword.postValue(true);
                userActual.postValue(user);
            }

            @Override
            public void passwordError() {
                updatePassword.postValue(false);
            }
        });
    }

    //  Restablece la variable del usuario actual
    public void restartDatosUsuario() {
        userActual = new MutableLiveData<>();
    }


}


