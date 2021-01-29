package com.example.proyectoapp.Login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectoapp.R;
import com.example.proyectoapp.Utils;
import com.example.proyectoapp.databinding.FragmentRegistroBinding;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;
    private NavController navController;
    private LoginViewModel loginViewModel;
    private User userInsertar = new User();

    private String usuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentRegistroBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        // Click en el boton registrarse
        binding.buttonRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = binding.editTextUsuario.getText().toString();
                String password = binding.editTextContrasenya.getText().toString();
                String passwordRepetir = binding.editTextRepetirContrasenya.getText().toString();
                // Siempre que todos los campos esten rellenados
                if (!usuario.equals("") && !password.equals("") && !passwordRepetir.equals("")) {
                    // Siempre que las dos contraseñas coincidan
                    if (password.equals(passwordRepetir)) {
                        userInsertar = new User(usuario, password);
                        loginViewModel.insertar(userInsertar);
                    }
                    // Las contraseñas no coinciden
                    else {
                        DynamicToast.makeWarning(getContext(), "¡Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    DynamicToast.makeWarning(getContext(), "Debes rellenar todos los campos.", Toast.LENGTH_LONG).show();
                }

            }
        });

        // Observo si se han podido insertar los datos
        loginViewModel.registroResult.observe(getViewLifecycleOwner(), observer -> {
                // Si se ha podido insertar los datos
                if (observer.equals(Utils.Valor.TRUE)) {
                    DynamicToast.makeSuccess(getContext(), "Usuario registrado con exito!", Toast.LENGTH_LONG).show();
                    //loginViewModel.registroResult.postValue(Utils.Valor.NULL);
                    navController.navigate(R.id.go_to_EventosFragment);
                } else if (observer.equals(Utils.Valor.FALSE)) {
                    DynamicToast.makeError(getContext(), "El usuario "+usuario+" ya existe", Toast.LENGTH_LONG).show();
                    loginViewModel.registroResult.postValue(Utils.Valor.NULL);
                }
        });

        // Click entrar como anonimo
        binding.loginAnonimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abro el fragment del mapa principal
                navController.navigate(R.id.go_to_EventosFragment);
            }
        });

    }

}