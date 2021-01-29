package com.example.proyectoapp.TabbedInfoEquipo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Equipo.Equipo;
import com.example.proyectoapp.Equipo.EquipoViewModel;
import com.example.proyectoapp.Jugadores.JugadorViewModel;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentDetallesEquipoBinding;
import com.example.proyectoapp.databinding.FragmentTabbedPartidosBinding;

import java.util.List;


public class DetallesEquipoFragment extends Fragment {
    FragmentDetallesEquipoBinding binding;
    private PartidosViewModel partidosViewModel;
    private EquipoViewModel equipoViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDetallesEquipoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);
        equipoViewModel = new ViewModelProvider(requireActivity()).get(EquipoViewModel.class);

        Partido partidoEquipo = partidosViewModel.seleccionado().getValue();

        String partido = partidosViewModel.equipoSeleccionado().getValue();

        equipoViewModel.obtenerEquipo(partidoEquipo.equipoLocal).observe(getViewLifecycleOwner(), listaEquipo -> {
            if (listaEquipo.size() == 0) {
                equipoViewModel.insertarDatosEquipo(new Equipo("FC Barcelona", "España", "Ronald Koeman", "29 nov. 1899", 28, 25.3f, 15, 12, "LaLiga", "Champions League", "Supercopa de España", "Copa del rey"));
                equipoViewModel.insertarDatosEquipo(new Equipo("Dinamo Kiev", "Rusia", "Ronald Koeman", "29 nov. 1899", 28, 25.3f, 15, 12, "LaLiga", "Champions League", "Supercopa de España", "Copa del rey"));
            }
        });


    }

    private Drawable obtenerBanderaPais(Equipo equipo) {
        Drawable pais = null;
        if (equipo.paisClub.equals("España")) {
            pais = getResources().getDrawable(R.drawable.espana);
        } else if (equipo.paisClub.equals("Rusia")) {
            pais = getResources().getDrawable(R.drawable.rusia);
        }
        return pais;
    }



}