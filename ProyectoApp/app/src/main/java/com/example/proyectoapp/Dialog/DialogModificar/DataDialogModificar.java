package com.example.proyectoapp.Dialog.DialogModificar;

public class DataDialogModificar {
    String title;
    String text;
    String textoBotonPositivo;
    String colorBotonPositivo;
    String textoEdittext;

    public DataDialogModificar(String title, String text, String textoBotonPositivo) {
        this.title = title;
        this.text = text;
        this.textoBotonPositivo = textoBotonPositivo;
    }

    public void setColorBotonPositivo(String colorBotonPositivo) {
        this.colorBotonPositivo = colorBotonPositivo;
    }

    public void setTextoEdittext(String textoEdittext) {
        this.textoEdittext = textoEdittext;
    }
}
