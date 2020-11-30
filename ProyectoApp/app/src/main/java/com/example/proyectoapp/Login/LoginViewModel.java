package com.example.proyectoapp.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    LoginManager loginManager;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginManager = new LoginManager(application);
    }

    // Obtengo los datos
    LiveData<Boolean> obtenerUser(String usuario, String password) {
        return loginManager.validarUser(usuario, password);
    }

    // Inserta los datos
    public void insertar(User user) {
        loginManager.insertar(user);
    }

    LiveData<List<User>> obtenerTodo() {
        return loginManager.obtenerTodo();
    }

}

