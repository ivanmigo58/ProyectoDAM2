package com.example.proyectoapp.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoapp.Utils;

public class LoginViewModel extends AndroidViewModel {

    private LoginManager loginManager;
    public static MutableLiveData<User> userActual = new MutableLiveData<>();

    // Variables que observan el fragment
    MutableLiveData<Utils.Valor> loginResult = new MutableLiveData<>();
    MutableLiveData<Utils.Valor> registroResult = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> updateUsuario = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> updatePassword = new MutableLiveData<>();
    public MutableLiveData<Boolean> mostrarPassword = new MutableLiveData<>();
    public MutableLiveData<Utils.Valor> eliminadoUsuario = new MutableLiveData<>();



    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginManager = new LoginManager(application);
    }

    // Obtengo los datos
    void obtenerUser(String usuario, String password) {
        loginManager.validarUser(usuario, password, new LoginManager.LoginCallback() {
            @Override
            public void cueandoLoginOk(User user) {
                loginResult.postValue(Utils.Valor.TRUE);
                userActual.postValue(user);
            }

            @Override
            public void cuandoLoginError() {
                loginResult.postValue(Utils.Valor.FALSE);
            }
        });
    }


    // Inserta los datos
    public void insertar(User user) {
        loginManager.insertar(user, new LoginManager.RegistroCallback() {
            @Override
            public void cuandoRegistroOk() {
                registroResult.postValue(Utils.Valor.TRUE);
                userActual.postValue(user);
            }

            @Override
            public void cuandoUsuarioYaExiste() {
                registroResult.postValue(Utils.Valor.FALSE);
                System.out.println("error usuario");
            }
        });

    }

    // Actualiza el usuario
    public void updateUsuario(User user, String nuevoUsuario) {
        loginManager.updateUsuario(user, nuevoUsuario, new LoginManager.UpdateUsuarioCallback() {
            @Override
            public void usuarioActualizadoOk() {
                updateUsuario.postValue(Utils.Valor.TRUE);
                userActual.postValue(user);
            }

            @Override
            public void usuarioError() {
                updateUsuario.postValue(Utils.Valor.FALSE);
            }
        });
    }

    // Actualiza la contraseña
    public void updatePassword(User user) {
        loginManager.updatePassword(user, new LoginManager.UpdatePasswordCallback() {
            @Override
            public void passwordActualziadoOk() {
                updatePassword.postValue(Utils.Valor.TRUE);
                userActual.postValue(user);
            }

            @Override
            public void passwordError() {
                updatePassword.postValue(Utils.Valor.FALSE);
            }
        });
    }

    // Elimina la cuenta del usuario
    public void eliminarUsuario(User user) {
        loginManager.eliminarUsuario(user, new LoginManager.EliminarUsuarioCallback() {
            @Override
            public void eliminarOk() {
                eliminadoUsuario.postValue(Utils.Valor.TRUE);
            }

            @Override
            public void eliminarError() {
                eliminadoUsuario.postValue(Utils.Valor.FALSE);
            }
        });
    }

    //  Restablece la variable del usuario actual
    public void restartDatosUsuario() {
        userActual = new MutableLiveData<>();
    }


}


