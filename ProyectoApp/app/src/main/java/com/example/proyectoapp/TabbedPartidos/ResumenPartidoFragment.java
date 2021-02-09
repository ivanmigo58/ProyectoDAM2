package com.example.proyectoapp.TabbedPartidos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.example.proyectoapp.Login.User;
import com.example.proyectoapp.R;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}