package com.example.proyectoapp.Dialog.DialogModificar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectoapp.Dialog.DialogViewModel;
import com.example.proyectoapp.databinding.FragmentDialogModificarBinding;

public class DialogModificarFragment extends DialogFragment {
    private FragmentDialogModificarBinding binding;
    private DialogViewModel dialogViewModel;
    private ClickDialogModificarCallback clickDialogCallback;

    public interface ClickDialogModificarCallback {
        public void clickButtonPositivo(String textoEditText);
    }

    public DialogModificarFragment(ClickDialogModificarCallback clickDialogCallback) {
        this.clickDialogCallback = clickDialogCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDialogModificarBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogViewModel = new ViewModelProvider(requireActivity()).get(DialogViewModel.class);
        setCancelable(false);

        // Cuando cambia actualizo los datos del dialog
        dialogViewModel.dataDialogModificar.observe(getViewLifecycleOwner(), new Observer<DataDialogModificar>() {
            @Override
            public void onChanged(DataDialogModificar dataDialogModificar) {
                binding.titulo.setText(dataDialogModificar.title);
                binding.texto.setText(dataDialogModificar.text);
                binding.botonPositivo.setText(dataDialogModificar.textoBotonPositivo);
                if (dataDialogModificar.colorBotonPositivo != null) {
                    binding.botonPositivo.setTextColor(Color.parseColor(dataDialogModificar.colorBotonPositivo));
                }
            }
        });

        // Click en el boton positivo
        binding.botonPositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDialogCallback.clickButtonPositivo(binding.editText.getText().toString());
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

