package com.example.proyectoapp.Ligas;

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

import com.example.proyectoapp.Competiciones.Competiciones;
import com.example.proyectoapp.Competiciones.CompeticionesViewModel;
import com.example.proyectoapp.Eventos.EventosFragment;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentLigasBinding;
import com.example.proyectoapp.databinding.ViewholderCompeticionBinding;
import com.example.proyectoapp.databinding.ViewholderListaligasBinding;

import java.util.List;


public class LigasFragment extends Fragment {
    FragmentLigasBinding binding;
    ViewholderListaligasBinding viewholderListaligasBinding;
    private CompeticionesViewModel competicionesViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentLigasBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        competicionesViewModel = new ViewModelProvider(requireActivity()).get(CompeticionesViewModel.class);


        LigasFragment.CompeticionesAdapter competicionesAdapter = new LigasFragment.CompeticionesAdapter();
        competicionesViewModel.obtenerCompeticiones().observe(getViewLifecycleOwner(), listaCompeticion -> {

            // En el caso de que la base de datos no tenga datos insertados, se los metemos
            if (listaCompeticion.size() == 0) {
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Champions League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Europa League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("LaLiga"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Premier League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Bundesliga"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Serie A"));
                competicionesViewModel.insertarDatosCompeticiones(new Competiciones("Ligue 1"));
            }
            competicionesAdapter.setCompeticionesList(listaCompeticion);
            binding.listaLigas.setAdapter(competicionesAdapter);
        });
    }

    class CompeticionesAdapter extends  RecyclerView.Adapter<LigasFragment.CompeticionesViewHolder> {

        private List<Competiciones> competicionesList;

        @NonNull
        @Override
        public LigasFragment.CompeticionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LigasFragment.CompeticionesViewHolder(ViewholderListaligasBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LigasFragment.CompeticionesViewHolder holder, int position) {
            Competiciones competiciones = competicionesList.get(position);
            holder.binding.nombreCompeticion.setText(competiciones.nombre);
            holder.binding.iconoCompeticion.setImageDrawable(obtenerImagen(competicionesList.get(position)));
        }

        @Override
        public int getItemCount() {
            if (competicionesList == null) {
                return 0;
            } else {
                return competicionesList.size();
            }
        }

        public void setCompeticionesList(List<Competiciones> competicionesList) {
            this.competicionesList = competicionesList;
            notifyDataSetChanged();
        }
    }

    class  CompeticionesViewHolder extends RecyclerView.ViewHolder {
        ViewholderListaligasBinding binding;

        public CompeticionesViewHolder(ViewholderListaligasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private Drawable obtenerImagen(Competiciones competiciones) {
        Drawable drawable = null;
        if (competiciones.nombre.equals("Champions League")) {
            drawable = getResources().getDrawable(R.drawable.champion_icon);
        } else if (competiciones.nombre.equals("Europa League")) {
            drawable = getResources().getDrawable(R.drawable.europaleague_icon);
        } else if (competiciones.nombre.equals("LaLiga")) {
            drawable = getResources().getDrawable(R.drawable.laliga_icon);
        } else if (competiciones.nombre.equals("Premier League")) {
            drawable = getResources().getDrawable(R.drawable.premier_icon);
        } else if (competiciones.nombre.equals("Bundesliga")) {
            drawable = getResources().getDrawable(R.drawable.bundesliga_icon);
        } else if (competiciones.nombre.equals("Serie A")) {
            drawable = getResources().getDrawable(R.drawable.serie_a_icon);
        } else if (competiciones.nombre.equals("Ligue 1")) {
            drawable = getResources().getDrawable(R.drawable.ligueone_icon);
        }

        return drawable;
    }
}