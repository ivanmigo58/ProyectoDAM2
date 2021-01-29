package com.example.proyectoapp.TabbedInfoEquipo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentTabbedEquipoBinding;
import com.google.android.material.tabs.TabLayoutMediator;


public class TabbedEquipoFragment extends Fragment {
    private FragmentTabbedEquipoBinding binding;
    private PartidosViewModel partidosViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        // Inflate the layout for this fragment
        return (binding = FragmentTabbedEquipoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);

        binding.viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0: default:
                        return new DetallesEquipoFragment();
                    case 1:
                        return new ResultadosFragment();
                    case 2:
                        return new ClasificacionFragment();
                }

            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("DETALLES");
                    break;
                case 1: default:
                    tab.setText("RESULTADOS");
                    break;
                case 2:
                    tab.setText("CLASIFICACIÓN");
                    break;
            }
        }).attach();

        partidosViewModel.equipoSeleccionado().observe(getViewLifecycleOwner(), equipo -> {
            binding.nombreEquipoPartido.setText(equipo);
            binding.escudoEquipo.setImageDrawable(obtenerEscudo(equipo));
        });
    }

    private Drawable obtenerEscudo(String equipo) {
        Drawable drawable = null;
        if (equipo.equals("FC Barcelona") ||  equipo.equals("FC Barcelona")) {
            drawable = getResources().getDrawable(R.drawable.escudo_barcelona);
        } else if (equipo.equals("Dinamo Kiev") || equipo.equals("Dinamo Kiev")) {
            drawable = getResources().getDrawable(R.drawable.dk);
        } else if (equipo.equals("Real Madrid")) {
            drawable = getResources().getDrawable(R.drawable.realmadrid_escudo);
        } else if (equipo.equals("Inter")) {
            drawable = getResources().getDrawable(R.drawable.intermilan_escudo);
        } else if (equipo.equals("Atalanta")) {
            drawable = getResources().getDrawable(R.drawable.atalanta_escudo);
        } else if (equipo.equals("Liverpool")) {
            drawable = getResources().getDrawable(R.drawable.liverpool_escudo);
        }

        return drawable;
    }
}