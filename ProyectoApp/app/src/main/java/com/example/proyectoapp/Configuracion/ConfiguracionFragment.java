package com.example.proyectoapp.Configuracion;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.proyectoapp.Login.LoginFragment;
import com.example.proyectoapp.Login.LoginViewModel;
import com.example.proyectoapp.Login.User;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentConfiguracionBinding;

public class ConfiguracionFragment extends Fragment {
    private FragmentConfiguracionBinding binding;
    private LoginViewModel loginViewModel;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.itemConfiguracion);
        return (binding = FragmentConfiguracionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        // Oculto el password
        binding.textViewPassword.setTransformationMethod(new PasswordTransformationMethod());
        // Cambiar usuario
        binding.buttonCambiarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Compruebo si se ha logeado como anonimo o no
                if (loginViewModel.userActual.getValue() != null) {
                    cambiarUsuario();
                } else {
                    new Utils().alertDialog(getActivity(), getString(R.string.error_titulo), getString(R.string.funcion_no_disponible_para_anonimo), getString(R.string.boton_aceptar)).show();
                }
            }
        });

        // Click en el boton ocultar contraseña
        binding.buttonOcultarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginViewModel.userActual.getValue() != null) {
                    loginViewModel.mostrarPassword.setValue(false);
                } else {
                    new Utils().alertDialog(getActivity(), getString(R.string.error_titulo), getString(R.string.funcion_no_disponible_para_anonimo), getString(R.string.boton_aceptar)).show();
                }
            }
        });


        // Click en el boton cambiar contraseña
        binding.buttonCambiarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginViewModel.userActual.getValue() != null) {
                    cambiarPassword();
                } else {
                    new Utils().alertDialog(getActivity(), getString(R.string.error_titulo), getString(R.string.funcion_no_disponible_para_anonimo), getString(R.string.boton_aceptar)).show();
                }
            }
        });

        // Cuando cambia el usuario actualizo los textview
        loginViewModel.userActual.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // Actualizo la variable de user
                ConfiguracionFragment.this.user = user;
                binding.textViewUsuario.setText(user.username);
                binding.textViewPassword.setText(user.password);
            }
        });

        // Cuando cambia la variable mostrar contraseña
        loginViewModel.mostrarPassword.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mostrarContrasenya();
                } else {
                    ocultarContrasenya();
                }
            }
        });


    }

    private void cambiarUsuario() {
        // Creo el alert dialog
        AlertDialog.Builder alertDialog = new Utils().alertDialog(getActivity(), getString(R.string.cambiar_usuario_titulo), getString(R.string.cambiar_usuario), getString(R.string.cancelar));
        final EditText editText = new EditText(getActivity());
        // Click boton aceptar
        alertDialog.setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nuevoUsuario = editText.getText().toString();
                user.username = nuevoUsuario;
                loginViewModel.updateUsuario(user, nuevoUsuario);
            }
        });
        // Creo el frame layout donde va el edit text personalizado
        FrameLayout frameLayout = new Utils().editText(getActivity(), editText);
        alertDialog.setView(frameLayout);
        alertDialog.show();

        // TODO NO SE SI ESTO PUEDE ESTAR AKI
        // Observa si se ha cambiado el usuario
        loginViewModel.updateUsuario.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(getContext(), getString(R.string.usuario_actualizado), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_actualizar_usuario), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void mostrarContrasenya() {
        // Cambio los botones
        binding.buttonVerPassword.setVisibility(View.GONE);
        binding.buttonOcultarPassword.setVisibility(View.VISIBLE);
        // Muestro el password
        binding.textViewPassword.setTransformationMethod(null);
    }


    private void ocultarContrasenya() {
        // Cambio los botones
        binding.buttonOcultarPassword.setVisibility(View.GONE);
        binding.buttonVerPassword.setVisibility(View.VISIBLE);
        // Oculto la contraseña
        binding.textViewPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    private void cambiarPassword() {
        // Creo el alert dialog
        AlertDialog.Builder alertDialog = new Utils().alertDialog(getActivity(),getString(R.string.cambiar_password_titulo), getString(R.string.cambiar_password), getString(R.string.cancelar));
        final EditText editText = new EditText(getActivity());
        // Click boton aceptar
        alertDialog.setPositiveButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.password = editText.getText().toString();
                loginViewModel.updatePassword(user);
            }
        });
        // Creo el frame layout donde va el edit text personalizado
        FrameLayout frameLayout = new Utils().editText(getActivity(), editText);
        alertDialog.setView(frameLayout);
        alertDialog.show();

        // Observa cuando cambia la variable update password
        loginViewModel.updatePassword.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(getContext(), getString(R.string.password_actualizado), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_actualizar_password), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // Click en el check de enviar correo
    private void clickCheck(boolean isChecked) {
        // Si esta clickado nuestro el linear
        if (isChecked) {
            binding.layoutDireccionCorreo.setVisibility(View.VISIBLE);
        }
        // Si no esta clickado, pongo invisible el linear
        else if (!isChecked) {
            binding.layoutDireccionCorreo.setVisibility(View.INVISIBLE);
        }
    }
}
