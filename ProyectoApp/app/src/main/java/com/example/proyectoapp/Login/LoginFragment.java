package com.example.proyectoapp.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.R;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.ActivityMainBinding;
import com.example.proyectoapp.databinding.FragmentLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private NavController navController;
    private LoginViewModel loginViewModel;

    private List<User> userList = new ArrayList<>();

    private String usuario = null;
    private String password = null;


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
                usuario = binding.editTextUsuario.getText().toString();
                password = binding.editTextContrasenya.getText().toString();
                loginViewModel.obtenerUser(usuario, password);


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

        obtenerUser().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    System.out.println("Correcto");
                } else {
                    System.out.println("Incorrecto");
                }
            }
        });

        obtenerTodo().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList = users;
            }
        });

    }

    // Obtengo usuario y contraseña de la base de datos
    LiveData<Boolean> obtenerUser() {
        return loginViewModel.obtenerUser(usuario, password);
    }

    // Obtengo todos los datos de la base de datos
    LiveData<List<User>> obtenerTodo() {
        return loginViewModel.obtenerTodo();
    }

}
