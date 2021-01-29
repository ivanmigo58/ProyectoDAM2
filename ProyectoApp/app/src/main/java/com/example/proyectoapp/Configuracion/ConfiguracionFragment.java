package com.example.proyectoapp.Configuracion;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.proyectoapp.Dialog.DialogConfirmar.DataDialogConfirmar;
import com.example.proyectoapp.Dialog.DialogConfirmar.DialogConfirmarFragment;
import com.example.proyectoapp.Dialog.DialogModificar.DataDialogModificar;
import com.example.proyectoapp.Dialog.DialogModificar.DialogModificarFragment;
import com.example.proyectoapp.Dialog.DialogViewModel;
import com.example.proyectoapp.Login.LoginFragment;
import com.example.proyectoapp.Login.LoginViewModel;
import com.example.proyectoapp.Login.User;
import com.example.proyectoapp.R;
import com.example.proyectoapp.Utils;
import com.example.proyectoapp.databinding.FragmentConfiguracionBinding;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class ConfiguracionFragment extends Fragment {
    private FragmentConfiguracionBinding binding;
    private LoginViewModel loginViewModel;
    private User user;
    private DialogViewModel dialogViewModel;
    private DialogModificarFragment dialogModificarFragment;
    private DialogConfirmarFragment dialogConfirmarFragment;
    private NavController navController;


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
        dialogViewModel = new ViewModelProvider(requireActivity()).get(DialogViewModel.class);
        navController = Navigation.findNavController(view);
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

        // Click en el boton borrar cuenta
        binding.buttonEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginViewModel.userActual.getValue() != null) {
                    eliminarCuenta();
                } else {
                    new Utils().alertDialog(getActivity(), getString(R.string.error_titulo), getString(R.string.funcion_no_disponible_para_anonimo), getString(R.string.boton_aceptar)).show();
                }
            }
        });

        // Cuando cambia el usuario, actualizo los textview
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

        // Observa si se ha cambiado el usuario
        loginViewModel.updateUsuario.observe(getViewLifecycleOwner(), observer -> {
                if (observer.equals(Utils.Valor.TRUE)) {
                    DynamicToast.makeSuccess(getContext(), getString(R.string.usuario_actualizado), Toast.LENGTH_LONG).show();
                    loginViewModel.updateUsuario.postValue(Utils.Valor.NULL);
                } else if (observer.equals(Utils.Valor.FALSE)) {
                    DynamicToast.makeError(getContext(), getString(R.string.error_actualizar_usuario), Toast.LENGTH_LONG).show();
                    loginViewModel.updateUsuario.postValue(Utils.Valor.NULL);
                }
        });


        // Observa cuando cambia la variable update password
        loginViewModel.updatePassword.observe(getViewLifecycleOwner(), observer -> {
                if (observer.equals(Utils.Valor.TRUE)) {
                    DynamicToast.makeSuccess(getContext(), getString(R.string.password_actualizado), Toast.LENGTH_LONG).show();
                    loginViewModel.updatePassword.postValue(Utils.Valor.NULL);
                } else if (observer.equals(Utils.Valor.FALSE)) {
                    DynamicToast.makeError(getContext(), getString(R.string.error_actualizar_password), Toast.LENGTH_LONG).show();
                    loginViewModel.updatePassword.postValue(Utils.Valor.NULL);
                }
        });

        // Observo la variable de cuando se ha eliminado la cuenta del usuario
        loginViewModel.eliminadoUsuario.observe(getViewLifecycleOwner(), observer -> {
            // Cuenta eliminada
            if (observer.equals(Utils.Valor.TRUE)) {
                DynamicToast.makeSuccess(getContext(), getString(R.string.usuario_eliminado), Toast.LENGTH_LONG).show();
                loginViewModel.eliminadoUsuario.postValue(Utils.Valor.NULL);
                navController.navigate(R.id.go_to_loginFragment);
            } else if (observer.equals(Utils.Valor.FALSE)) {
                DynamicToast.makeError(getContext(), getString(R.string.error_eliminar_usuario), Toast.LENGTH_LONG).show();
                loginViewModel.eliminadoUsuario.postValue(Utils.Valor.NULL);
            }
        });
    }

    // Cambiar usuario
    private void cambiarUsuario() {
        // Transicion del dialogo
        FragmentTransaction ft = new Utils().fragmentTransaction(this);
        // Creo el alert dialog
        dialogModificarFragment = new DialogModificarFragment(new DialogModificarFragment.ClickDialogModificarCallback() {
            // Click boton positivo
            @Override
            public void clickButtonPositivo(String textoEditText) {
                if (!textoEditText.equals("")) {
                    user.username = textoEditText;
                    loginViewModel.updateUsuario(user, user.username);
                    dialogModificarFragment.dismiss();
                }
            }

        });

        dialogModificarFragment.show(ft, "dialog");
        // Los datos del dialog
        DataDialogModificar dataDialogModificar = new DataDialogModificar(getString(R.string.modificar_usuario_titulo), getString(R.string.modificar_usuario), getString(R.string.modificar));
        dataDialogModificar.setColorBotonPositivo(getString(R.color.azulPrincipal));
        dialogViewModel.dataDialogModificar.postValue(dataDialogModificar);

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
        // Transicion del dialogo
        FragmentTransaction ft = new Utils().fragmentTransaction(this);
        // Creo el alert dialog
        dialogModificarFragment = new DialogModificarFragment(new DialogModificarFragment.ClickDialogModificarCallback() {
            // Click boton positivo
            @Override
            public void clickButtonPositivo(String textoEditText) {
                if (!textoEditText.equals("")) {
                    user.password = textoEditText;
                    loginViewModel.updatePassword(user);
                    dialogModificarFragment.dismiss();
                }
            }
        });

        dialogModificarFragment.show(ft, "dialog");
        // Los datos del dialog
        DataDialogModificar dataDialogModificar = new DataDialogModificar(getString(R.string.modificar_password_titulo), getString(R.string.modificar_password), getString(R.string.modificar));
        dataDialogModificar.setColorBotonPositivo(getString(R.color.azulPrincipal));
        dialogViewModel.dataDialogModificar.postValue(dataDialogModificar);
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

    // Metodo para eliminar cuenta
    private void eliminarCuenta() {
        dialogConfirmarFragment = new DialogConfirmarFragment(new DialogConfirmarFragment.ClickDialogConfirmarCallback() {
            @Override
            public void clickButtonPositivo() {
                loginViewModel.eliminarUsuario(LoginViewModel.userActual.getValue());
                dialogConfirmarFragment.dismiss();
            }
        });
        DataDialogConfirmar dataDialogConfirmar = new DataDialogConfirmar(getString(R.string.eliminar_cuenta), getString(R.string.texto_eliminar_cuenta), getString(R.string.eliminar));
        dataDialogConfirmar.setColorTitle(getString(R.color.rojo));
        //dataDialogConfirmar.setIcon(R.drawable.eliminar);
        dataDialogConfirmar.setColorIcon(getString(R.color.rojo));
        dataDialogConfirmar.setColorBotonPositivo(getString(R.color.rojo));
        dialogViewModel.dataDialogConfirmar.postValue(dataDialogConfirmar);
        dialogConfirmarFragment.show(getChildFragmentManager(), "Dialog");
    }
}
