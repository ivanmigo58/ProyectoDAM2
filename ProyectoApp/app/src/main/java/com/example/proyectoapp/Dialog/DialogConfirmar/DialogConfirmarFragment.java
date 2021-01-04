package com.example.proyectoapp.Dialog.DialogConfirmar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Dialog.DialogViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentDialogConfirmarBinding;

public class DialogConfirmarFragment extends DialogFragment {
    private FragmentDialogConfirmarBinding binding;
    private DialogViewModel dialogViewModel;
    private ClickDialogConfirmarCallback clickDialogCallback;

    public DialogConfirmarFragment(ClickDialogConfirmarCallback clickDialogCallback) {
        this.clickDialogCallback = clickDialogCallback;
    }

    public interface ClickDialogConfirmarCallback {
        void clickButtonPositivo();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDialogConfirmarBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogViewModel = new ViewModelProvider(requireActivity()).get(DialogViewModel.class);
        setCancelable(false);
        // Cuando cambia actualizo los datos del dialog
        dialogViewModel.dataDialogConfirmar.observe(getViewLifecycleOwner(), new Observer<DataDialogConfirmar>() {
            @Override
            public void onChanged(DataDialogConfirmar dataDialogConfirmar) {
                binding.titulo.setText(dataDialogConfirmar.title);
                binding.texto.setText(dataDialogConfirmar.text);
                binding.botonPositivo.setText(dataDialogConfirmar.textoBotonPositivo);
                if (dataDialogConfirmar.colorBotonPositivo != null) {
                    binding.botonPositivo.setTextColor(Color.parseColor(dataDialogConfirmar.colorBotonPositivo));
                }
                if (dataDialogConfirmar.colorTitle != null) {
                    binding.titulo.setTextColor(Color.parseColor(dataDialogConfirmar.colorTitle));
                }
                if (dataDialogConfirmar.icon != -1) {
                    binding.icono.setImageResource(dataDialogConfirmar.icon);
                }
                if (dataDialogConfirmar.colorIcon != null) {
                    binding.icono.setColorFilter(Color.parseColor(dataDialogConfirmar.colorIcon));
                }
            }
        });

        // Click en el boton positivo
        binding.botonPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialogCallback.clickButtonPositivo();
            }
        });

        // Click en el boton negativo, se cierra el dialogo
        binding.botonNegativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}