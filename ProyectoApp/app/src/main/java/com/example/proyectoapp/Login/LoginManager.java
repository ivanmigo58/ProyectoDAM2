package com.example.proyectoapp.Login;

import android.app.Application;
import android.util.JsonReader;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LoginManager {
    DaoBaseDeDatos daoBaseDeDatos;
    Executor executor = Executors.newSingleThreadExecutor();

    // Constructor
    LoginManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    LiveData<Boolean> validarUser(String usuario, String password) {
        MutableLiveData<Boolean> respuesta = new MutableLiveData<>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                User user = daoBaseDeDatos.obtenerUser(usuario, password);
                // Si el usuario y password son correctos
                if (user != null) {
                    respuesta.postValue(true);
//                    System.out.println("correcto");
                    System.out.println(user.username + " " + user.password);
                } else {
//                    System.out.println("incorrect");
                    respuesta.postValue(false);
                }
            }
        });
        return respuesta;
    }

    // Inserta datos
    void insertar(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                daoBaseDeDatos.insertar(user);
            }
        });
    }

    LiveData<List<User>> obtenerTodo() {
        return daoBaseDeDatos.obtenerTodo();
    }

}

