package com.example.proyectoapp.Clasificacion;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.TabbedPrincipal.EventosFragment;
import com.example.proyectoapp.databinding.FragmentClasificacionChampionsBinding;
import com.example.proyectoapp.databinding.ViewholderClasificacionchampionsBinding;
import com.example.proyectoapp.databinding.ViewholderCompeticionBinding;
import com.example.proyectoapp.databinding.ViewholderGrupochampionsBinding;

import java.util.List;

public class ClasificacionChampionsFragment extends Fragment {
    FragmentClasificacionChampionsBinding binding;
    private GruposChampionsViewModel gruposChampionsViewModel;
    private ClasificacionChampionsViewModel clasificacionChampionsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Champions League");
        // Inflate the layout for this fragment
        return (binding = FragmentClasificacionChampionsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gruposChampionsViewModel = new ViewModelProvider(requireActivity()).get(GruposChampionsViewModel.class);
        clasificacionChampionsViewModel = new ViewModelProvider(requireActivity()).get(ClasificacionChampionsViewModel.class);



        GrupoChampionsAdapter grupoChampionsAdapter = new GrupoChampionsAdapter();
        gruposChampionsViewModel.obtenerGrupos().observe(getViewLifecycleOwner(), listaGrupos ->{
            if (listaGrupos.size() == 0) {
                gruposChampionsViewModel.insertarDatosGrupos(new GruposChampions("A"));
                gruposChampionsViewModel.insertarDatosGrupos(new GruposChampions("B"));
                gruposChampionsViewModel.insertarDatosGrupos(new GruposChampions("C"));
            }
            grupoChampionsAdapter.setGruposChampionsList(listaGrupos);
            binding.listaGruposChampions.setAdapter(grupoChampionsAdapter);
        });



    }



    class GrupoChampionsAdapter extends RecyclerView.Adapter<GrupoChampionsViewHolder> {

        private List<GruposChampions> gruposChampionsList;

        @NonNull
        @Override
        public GrupoChampionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GrupoChampionsViewHolder(ViewholderGrupochampionsBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull GrupoChampionsViewHolder holder, int position) {
            GruposChampions gruposChampions = gruposChampionsList.get(position);
            holder.binding.grupoChampions.setText("GRUPO " + gruposChampions.letraGrupo);

            clasificacionChampionsViewModel.eliminarEquiposChampions();

            ClasificacionChampionsAdapter clasificacionChampionsAdapter = new ClasificacionChampionsAdapter();
            clasificacionChampionsViewModel.obtenerEquiposClasificacion().observe(getViewLifecycleOwner(), listaEquiposClasificacion -> {
                if (listaEquiposClasificacion.size() == 0) {
                    clasificacionChampionsViewModel.insertarDatosClasificacionChampions(new ClasificacionChampions(1,"FC Barcelona", 3,7,9 , "C"));
                    clasificacionChampionsViewModel.insertarDatosClasificacionChampions(new ClasificacionChampions(2,"Juventus", 3,7,9 , "C"));
                    clasificacionChampionsViewModel.insertarDatosClasificacionChampions(new ClasificacionChampions(3,"Dinamo kiev", 3,7,9 , "C"));
                    clasificacionChampionsViewModel.insertarDatosClasificacionChampions(new ClasificacionChampions(4,"Ferencvaros", 3,7,9 , "C"));
                }

                clasificacionChampionsAdapter.setClasificacionChampionsList(listaEquiposClasificacion);
                holder.binding.listaEquiposClasificacion.setAdapter(clasificacionChampionsAdapter);

            });



        }

        @Override
        public int getItemCount() {
            if (gruposChampionsList == null) {
                return 0;
            } else {
                return gruposChampionsList.size();
            }
        }


        public void setGruposChampionsList(List<GruposChampions> gruposChampionsList) {
            this.gruposChampionsList = gruposChampionsList;
            notifyDataSetChanged();
        }

    }

    class GrupoChampionsViewHolder extends RecyclerView.ViewHolder {
        ViewholderGrupochampionsBinding binding;

        public GrupoChampionsViewHolder(ViewholderGrupochampionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    class ClasificacionChampionsAdapter extends RecyclerView.Adapter<ClasificacionChampionsViewHolder> {

        private List<ClasificacionChampions> clasificacionChampionsList;

        @NonNull
        @Override
        public ClasificacionChampionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ClasificacionChampionsViewHolder(ViewholderClasificacionchampionsBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ClasificacionChampionsViewHolder holder, int position) {
            ClasificacionChampions clasificacionChampions = clasificacionChampionsList.get(position);
            holder.binding.pos2.setText(String.valueOf(clasificacionChampions.posicion));
            holder.binding.equipo2.setText(clasificacionChampions.nombreEquipo);
            holder.binding.numPartidos.setText(String.valueOf(clasificacionChampions.partidosJugados));
            holder.binding.diferencia2.setText(String.valueOf(clasificacionChampions.diferenciaGoles));
            holder.binding.puntos2.setText(String.valueOf(clasificacionChampions.puntos));



        }

        @Override
        public int getItemCount() {
            if (clasificacionChampionsList == null) {
                return 0;
            } else {
                return clasificacionChampionsList.size();
            }
        }

        public void setClasificacionChampionsList(List<ClasificacionChampions> clasificacionChampionsList) {
            this.clasificacionChampionsList = clasificacionChampionsList;
            notifyDataSetChanged();
        }
    }

    class ClasificacionChampionsViewHolder extends RecyclerView.ViewHolder {
        ViewholderClasificacionchampionsBinding binding;

        public ClasificacionChampionsViewHolder(ViewholderClasificacionchampionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}