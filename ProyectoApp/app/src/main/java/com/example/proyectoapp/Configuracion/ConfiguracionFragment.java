package com.example.proyectoapp.Configuracion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.proyectoapp.Login.LoginFragment;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentConfiguracionBinding;

public class ConfiguracionFragment extends Fragment {
    FragmentConfiguracionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.itemConfiguracion);
        return (binding = FragmentConfiguracionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Click en el boton de ver contraseña
        binding.buttonVerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambio los botones
                binding.buttonVerPassword.setVisibility(View.GONE);
                binding.buttonOcultarPassword.setVisibility(View.VISIBLE);
                // Muestro el password
                binding.textViewPassword.setText(LoginFragment.password);
            }
        });


        // Click en el boton ocultar contraseña
        binding.buttonOcultarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambio los botones
                binding.buttonOcultarPassword.setVisibility(View.GONE);
                binding.buttonVerPassword.setVisibility(View.VISIBLE);
                // Oculto la contraseña
                binding.textViewPassword.setText("******");
            }
        });

    }
}
