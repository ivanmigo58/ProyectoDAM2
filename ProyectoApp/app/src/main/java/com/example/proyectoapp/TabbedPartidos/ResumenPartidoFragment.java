package com.example.proyectoapp.TabbedPartidos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.databinding.FragmentResumenPartidoBinding;
import com.example.proyectoapp.databinding.FragmentTabbedPartidosBinding;


public class ResumenPartidoFragment extends Fragment {
    FragmentResumenPartidoBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentResumenPartidoBinding.inflate(inflater, container, false)).getRoot();
    }
}