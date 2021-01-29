package com.example.proyectoapp.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectoapp.R;
import com.example.proyectoapp.R;
import com.example.proyectoapp.Utils;
import com.example.proyectoapp.databinding.ActivityMainBinding;
import com.example.proyectoapp.databinding.FragmentLoginBinding;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private NavController navController;
    private LoginViewModel loginViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        // Inflate the layout for this fragment
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Enlazo el navcontroller
        navController = Navigation.findNavController(view);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        // Click button login
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = binding.editTextUsuario.getText().toString();
                String password = binding.editTextContrasenya.getText().toString();
                // Siempre que estan commpletos
                if (!usuario.equals("") && !password.equals("")) {
                    loginViewModel.obtenerUser(usuario, password);
                } else {
                    DynamicToast.makeWarning(getContext(), "Tienes que introducir usuario y contraseña.").show();
                }
            }
        });

        // Click button registrarte
        binding.buttonRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abro el fragment de registrarse
                navController.navigate(R.id.action_loginFragment_to_registroFragment);
            }
        });

        // Click entrar como anonimo
        binding.loginAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abro el fragment de eventos
                navController.navigate(R.id.go_to_EventosFragment);
            }
        });

        // Obtengo usuario y contraseña de la base de datos
        loginViewModel.loginResult.observe(getViewLifecycleOwner(), valor -> {
            // Login correcto
            if (valor.equals(Utils.Valor.TRUE)) {
                // Reseteo la variable, para que al cerrar sesion no se guarde el resultado
                loginViewModel.loginResult.postValue(Utils.Valor.NULL);
                navController.navigate(R.id.go_to_EventosFragment);
            }
            // Login incorrecto
            else if (valor.equals(Utils.Valor.FALSE)) {
                DynamicToast.makeError(getContext(), "Usuario o contraseña incorrecta!").show();
                loginViewModel.loginResult.postValue(Utils.Valor.NULL);
            }
        });

    }
}
