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

    interface LoginLlamadaVuelta {
        void cueandoLoginOk();
        void cuandoLoginError();
    }

    interface RegistroLlamadaVuelta {
        void cuandoRegistroOk();
        void cuandoUsuarioYaExiste();
    }


    // Constructor
    LoginManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    void validarUser(String usuario, String password, LoginLlamadaVuelta loginLlamadaVuelta) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                User user = daoBaseDeDatos.obtenerUser(usuario, password);
                // Si el usuario y password son correctos
                if (user != null) {
                    loginLlamadaVuelta.cueandoLoginOk();
                } else {
                    loginLlamadaVuelta.cuandoLoginError();
                }
            }
        });

    }

    // Inserta datos
    void insertar(User user, RegistroLlamadaVuelta registroLlamadaVuelta) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Compruebo que no exista ese usuario en la base de datos
                if (daoBaseDeDatos.comprobarUsuario(user.username) == null) {
                    daoBaseDeDatos.insertar(user);
                    registroLlamadaVuelta.cuandoRegistroOk();
                }

                // Si no se ha podido hacer insert
                else {
                    registroLlamadaVuelta.cuandoUsuarioYaExiste();
                }
            }
        });
    }


}

