package com.example.proyectoapp.TabbedInfoEquipo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentDetallesEquipoBinding;
import com.example.proyectoapp.databinding.FragmentTabbedPartidosBinding;


public class DetallesEquipoFragment extends Fragment {
    FragmentDetallesEquipoBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDetallesEquipoBinding.inflate(inflater, container, false)).getRoot();
    }


}