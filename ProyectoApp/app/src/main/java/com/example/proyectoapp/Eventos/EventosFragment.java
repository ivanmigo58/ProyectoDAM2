package com.example.proyectoapp.Eventos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.databinding.FragmentEventosBinding;

import static android.view.View.GONE;

public class EventosFragment extends Fragment {
    FragmentEventosBinding binding;

    boolean partidosDesplegados = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return (binding = FragmentEventosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Clickar en el LinearLayout de alguna competicion
        binding.linearLayoutChampions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!partidosDesplegados) {
                    binding.partidos.setVisibility(View.VISIBLE);
                    desplegarPartidos(view);
                    partidosDesplegados = true;
                } else {
                    binding.partidos.setVisibility(GONE);
                    recogerPartidos(view);
                    partidosDesplegados = false;
                }
            }
        });



    }

    private void desplegarPartidos(View view) {
        binding.buttonDesplegarPartidos.setVisibility(GONE);
        binding.buttobRecogerPartidos.setVisibility(View.VISIBLE);
    }

    private void recogerPartidos (View view) {
        binding.buttobRecogerPartidos.setVisibility(GONE);
        binding.buttonDesplegarPartidos.setVisibility(View.VISIBLE);
    }


}
