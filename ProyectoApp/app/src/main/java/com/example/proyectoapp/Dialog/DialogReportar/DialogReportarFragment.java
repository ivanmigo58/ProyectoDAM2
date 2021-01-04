package com.example.proyectoapp.Dialog.DialogReportar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentDialogReportarBinding;

public class DialogReportarFragment extends DialogFragment {
    private FragmentDialogReportarBinding binding;
    public String title;
    ClickDialogReportarCallback clickDialogReportarCallback;

    public interface ClickDialogReportarCallback {
        void clickButtonReportar(String comentario);
    }

    // Constructor en el que le paso el titulo
    public DialogReportarFragment(String title, ClickDialogReportarCallback clickDialogReportarCallback) {
        this.title = title;
        this.clickDialogReportarCallback = clickDialogReportarCallback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDialogReportarBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
        // Actualizo el titulo
        binding.titulo.setText(title);
        // Click en el switch
        binding.switchDialogReportar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.editText.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    binding.editText.setVisibility(View.GONE);
                }
            }
        });

        // Click en el boton reportar
        binding.botonPositivo.setOnClickListener(c -> {
            clickDialogReportarCallback.clickButtonReportar(binding.editText.getText().toString());
        });

        // Click boton negativo
        binding.botonNegativo.setOnClickListener(c -> {
            dismiss();
        });

    }
}