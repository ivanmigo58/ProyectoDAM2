package com.example.proyectoapp.Dialog.DialogConfirmar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

import com.example.proyectoapp.Dialog.DialogModificar.DataDialogModificar;

public class DataDialogConfirmar{
    String title;
    String text;
    String textoBotonPositivo;
    String colorBotonPositivo;
    String colorTitle;
    int icon;
    String colorIcon;

    public DataDialogConfirmar(String title, String text, String textoBotonPositivo) {
        this.title = title;
        this.text = text;
        this.textoBotonPositivo = textoBotonPositivo;
        this.icon = -1;
    }

    public void setColorBotonPositivo(String colorBotonPositivo) {
        this.colorBotonPositivo = colorBotonPositivo;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setColorIcon(String colorIcon) {
        this.colorIcon = colorIcon;
    }
}
