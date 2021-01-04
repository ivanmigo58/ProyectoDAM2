package com.example.proyectoapp.Login;

import android.app.Application;


import com.example.proyectoapp.Data.BaseDeDatos;
import com.example.proyectoapp.Data.DaoBaseDeDatos;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LoginManager {
    DaoBaseDeDatos daoBaseDeDatos;
    Executor executor = Executors.newSingleThreadExecutor();

    interface LoginCallback {
        void cueandoLoginOk(User user);
        void cuandoLoginError();
    }

    interface RegistroCallback {
        void cuandoRegistroOk();
        void cuandoUsuarioYaExiste();
    }

    interface UpdateUsuarioCallback {
        void usuarioActualizadoOk();
        void usuarioError();
    }


    interface UpdatePasswordCallback {
        void passwordActualziadoOk();
        void passwordError();
    }

    interface EliminarUsuarioCallback {
        void eliminarOk();
        void eliminarError();
    }



    // Constructor
    LoginManager(Application application) {
        daoBaseDeDatos = BaseDeDatos.obtenerInstancia(application).daoBaseDeDatos();
    }

    void validarUser(String usuario, String password, LoginCallback loginCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                User user = daoBaseDeDatos.obtenerUser(usuario, password);
                // Si el usuario y password son correctos
                if (user != null) {
                    loginCallback.cueandoLoginOk(user);
                } else {
                    loginCallback.cuandoLoginError();
                }
            }
        });

    }

    // Inserta datos
    void insertar(User user, RegistroCallback registroCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Compruebo que no exista ese usuario en la base de datos
                if (daoBaseDeDatos.comprobarUsuario(user.username) == null) {
                    daoBaseDeDatos.insertar(user);
                    registroCallback.cuandoRegistroOk();
                }

                // Si no se ha podido hacer insert
                else {
                    registroCallback.cuandoUsuarioYaExiste();
                }
            }
        });
    }

    // Actualizar usuario
    void updateUsuario(User user, String nuevoUsuario, UpdateUsuarioCallback updateUsuarioCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Actualizo los datos
                daoBaseDeDatos.updateUsuario(user);
                // Compruebo que exista el nuevo usuario
                if (daoBaseDeDatos.comprobarUsuario(nuevoUsuario) != null) {
                    updateUsuarioCallback.usuarioActualizadoOk();
                }
                // Error al actualizar datos
                else {
                    updateUsuarioCallback.usuarioError();
                }
            }
        });
    }

    // Actualiza la contraseña
    void updatePassword(User user, UpdatePasswordCallback updatePasswordCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Actualizado los datos
                daoBaseDeDatos.updatePassword(user);
                // Compruebo que exista el usuario
                if (daoBaseDeDatos.obtenerUser(user.username, user.password) != null) {
                    updatePasswordCallback.passwordActualziadoOk();
                } else {
                    updatePasswordCallback.passwordError();
                }
            }
        });
    }

    // Elimina una cuenta de usuario
    void eliminarUsuario(User user, EliminarUsuarioCallback eliminarUsuarioCallback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Elimino la cuenta
                daoBaseDeDatos.eliminarUsuario(user);
                // Compruebo que no exista el usuario
                if (daoBaseDeDatos.obtenerUser(user.username, user.password) == null) {
                    eliminarUsuarioCallback.eliminarOk();
                } else {
                    eliminarUsuarioCallback.eliminarError();
                }
            }
        });
    }



}

