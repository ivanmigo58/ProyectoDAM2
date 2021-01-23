package com.example.proyectoapp.TabbedPartidos;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.TabbedPartidos.AlineacionesFragment;
import com.example.proyectoapp.TabbedPartidos.EstadisticasFragment;
import com.example.proyectoapp.TabbedPartidos.ResumenPartidoFragment;
import com.example.proyectoapp.databinding.FragmentTabbedPartidosBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabbedPartidosFragment extends Fragment {
    private FragmentTabbedPartidosBinding binding;
    private PartidosViewModel partidosViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        // Inflate the layout for this fragment
        return (binding = FragmentTabbedPartidosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);
        navController = NavHostFragment.findNavController(requireParentFragment());


        binding.viewPager.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0: default:
                        return new ResumenPartidoFragment();

                    case 1:
                        return new EstadisticasFragment();

                    case 2:
                        return new AlineacionesFragment();
                }

            }

            // El numero de TABS que tenemos
            @Override
            public int getItemCount() {
                return 3;
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("RESUMEN");
                    break;
                case 1: default:
                    tab.setText("ESTADÍSTICAS");
                    break;
                case 2:
                    tab.setText("ALINEACIONES");
                    break;
            }
        }).attach();


        partidosViewModel.seleccionado().observe(getViewLifecycleOwner(), partido -> {
            binding.nombreLocal.setText(partido.equipoLocal);
            binding.nombreVisitante.setText(partido.equipoVisitante);
            binding.fechaPartido.setText(partido.fecha);
            binding.horaInicioPartido.setText(partido.horaInicio);
            binding.nombreCompeticionPartido.setText(partido.competicion);
            binding.minutoEnVivo.setText(partido.minPartido);
            binding.resultadoLocal.setText(partido.resultadoLocal);
            binding.resultadoVisitante.setText(partido.resultadoVisitante);
            binding.escudoLocal.setImageDrawable(obtenerEscudoLocal(partido));
            binding.escudoVisitante.setImageDrawable(obtenerEscudoVisitante(partido));
            binding.escudoLocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partidosViewModel.seleccionarEquipoLocal(partido);
                    navController.navigate(R.id.go_to_infoEquipo);
                }
            });

            binding.escudoVisitante.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partidosViewModel.seleccionarEquipoVisitante(partido);
                    navController.navigate(R.id.go_to_infoEquipo);
                }
            });
        });

/*
        binding.botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

 */

    }

    private Drawable obtenerEscudoLocal(Partido partido) {
        Drawable drawable = null;
        if (partido.equipoLocal.equals("FC Barcelona") ) {
            drawable = getResources().getDrawable(R.drawable.escudo_barcelona);
        } else if (partido.equipoLocal.equals("Dinamo Kiev")) {
            drawable = getResources().getDrawable(R.drawable.dk);
        } else if (partido.equipoLocal.equals("Real Madrid")) {
            drawable = getResources().getDrawable(R.drawable.realmadrid_escudo);
        } else if (partido.equipoLocal.equals("Inter")) {
            drawable = getResources().getDrawable(R.drawable.intermilan_escudo);
        } else if (partido.equipoLocal.equals("Atalanta")) {
            drawable = getResources().getDrawable(R.drawable.atalanta_escudo);
        } else if (partido.equipoLocal.equals("Liverpool")) {
            drawable = getResources().getDrawable(R.drawable.liverpool_escudo);
        }

        return drawable;
    }

    private Drawable obtenerEscudoVisitante(Partido partido) {
        Drawable drawable = null;
        if (partido.equipoVisitante.equals("FC Barcelona") ) {
            drawable = getResources().getDrawable(R.drawable.escudo_barcelona);
        } else if (partido.equipoVisitante.equals("Dinamo Kiev")) {
            drawable = getResources().getDrawable(R.drawable.dk);
        } else if (partido.equipoVisitante.equals("Real Madrid")) {
            drawable = getResources().getDrawable(R.drawable.realmadrid_escudo);
        } else if (partido.equipoVisitante.equals("Inter")) {
            drawable = getResources().getDrawable(R.drawable.intermilan_escudo);
        } else if (partido.equipoVisitante.equals("Atalanta")) {
            drawable = getResources().getDrawable(R.drawable.atalanta_escudo);
        } else if (partido.equipoVisitante.equals("Liverpool")) {
            drawable = getResources().getDrawable(R.drawable.liverpool_escudo);
        }

        return drawable;
    }




}