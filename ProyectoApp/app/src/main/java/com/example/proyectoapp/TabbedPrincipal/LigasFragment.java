package com.example.proyectoapp.TabbedPrincipal;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Competiciones.CompeticionesViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentLigasBinding;
import com.example.proyectoapp.databinding.ViewholderListaligasBinding;

import java.util.List;


public class LigasFragment extends Fragment {
    FragmentLigasBinding binding;
    ViewholderListaligasBinding viewholderListaligasBinding;
    private CompeticionesViewModel competicionesViewModel;
    private NavController navController;


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

        navController = NavHostFragment.findNavController(requireParentFragment());


        CompeticionesAdapter competicionesAdapter = new CompeticionesAdapter();
        competicionesViewModel.obtenerCompeticiones().observe(getViewLifecycleOwner(), listaCompeticion -> {

            // En el caso de que la base de datos no tenga datos insertados, se los metemos
            if (listaCompeticion.size() == 0) {
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Champions League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Europa League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("LaLiga"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Premier League"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Bundesliga"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Serie A"));
                competicionesViewModel.insertarDatosCompeticiones(new Competicion("Ligue 1"));
            }
            competicionesAdapter.setCompeticionList(listaCompeticion);
            binding.listaLigas.setAdapter(competicionesAdapter);
        });
    }





    class CompeticionesAdapter extends  RecyclerView.Adapter<LigasFragment.CompeticionesViewHolder> {

        private List<Competicion> competicionList;

        @NonNull
        @Override
        public LigasFragment.CompeticionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LigasFragment.CompeticionesViewHolder(ViewholderListaligasBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull LigasFragment.CompeticionesViewHolder holder, int position) {
            Competicion competicion = competicionList.get(position);
            holder.binding.nombreCompeticion.setText(competicion.nombre);
            holder.binding.iconoCompeticion.setImageDrawable(obtenerImagen(competicionList.get(position)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    competicionesViewModel.seleccionar(competicion);
                    if (competicion.nombre.equals("Champions League")) {
                        navController.navigate(R.id.go_to_clasificacionChampions);
                    } else {
                        navController.navigate(R.id.go_to_clasificacionLiga);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            if (competicionList == null) {
                return 0;
            } else {
                return competicionList.size();
            }
        }

        public void setCompeticionList(List<Competicion> competicionList) {
            this.competicionList = competicionList;
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

    private Drawable obtenerImagen(Competicion competicion) {
        Drawable drawable = null;
        if (competicion.nombre.equals("Champions League")) {
            drawable = getResources().getDrawable(R.drawable.champion_icon);
        } else if (competicion.nombre.equals("Europa League")) {
            drawable = getResources().getDrawable(R.drawable.europaleague_icon);
        } else if (competicion.nombre.equals("LaLiga")) {
            drawable = getResources().getDrawable(R.drawable.laliga_icon);
        } else if (competicion.nombre.equals("Premier League")) {
            drawable = getResources().getDrawable(R.drawable.premier_icon);
        } else if (competicion.nombre.equals("Bundesliga")) {
            drawable = getResources().getDrawable(R.drawable.bundesliga_icon);
        } else if (competicion.nombre.equals("Serie A")) {
            drawable = getResources().getDrawable(R.drawable.serie_a_icon);
        } else if (competicion.nombre.equals("Ligue 1")) {
            drawable = getResources().getDrawable(R.drawable.ligueone_icon);
        }

        return drawable;
    }
}