package com.example.proyectoapp;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectoapp.Configuracion.ConfiguracionFragment;
import com.example.proyectoapp.Login.LoginViewModel;

public class Utils {

    // Devuelve un alert dialog basico
    public AlertDialog.Builder alertDialog(FragmentActivity activity, String titulo, String texto, String boton) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(texto);
        alertDialog.setNegativeButton(boton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Click en este boton no se hace nada
            }
        });
        return alertDialog;
    }


    // Retorna la transicion del dialog modificar
    public FragmentTransaction fragmentTransaction(ConfiguracionFragment configuracionFragment) {
        FragmentTransaction ft = configuracionFragment.getFragmentManager().beginTransaction();
        Fragment prev = configuracionFragment.getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    // Funcion que obtiene el nombre de usuario de la sesion actual
    public String obtenerUsername() {
        String usuario = null;
        if (LoginViewModel.userActual.getValue() != null) {
            usuario = LoginViewModel.userActual.getValue().username;
        } else {
            usuario = "anonimo";
        }
        return usuario;
    }

    public enum Valor {
        NULL,TRUE,FALSE
    }
}

