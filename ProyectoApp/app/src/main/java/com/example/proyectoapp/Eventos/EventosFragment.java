package com.example.proyectoapp.Eventos;

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
import com.example.proyectoapp.Partidos.Partidos;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentEventosBinding;
import com.example.proyectoapp.databinding.ViewholderCompeticionBinding;
import com.example.proyectoapp.databinding.ViewholderPartidosBinding;

import java.util.List;

import static android.view.View.GONE;

public class EventosFragment extends Fragment {
    FragmentEventosBinding binding;
    ViewholderCompeticionBinding viewholderCompeticionBinding;
    private PartidosViewModel partidosViewModel;
    private CompeticionesViewModel competicionesViewModel;


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
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);
        competicionesViewModel = new ViewModelProvider(requireActivity()).get(CompeticionesViewModel.class);

        CompeticionesAdapter competicionesAdapter = new CompeticionesAdapter();
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
            binding.listaCompeticiones.setAdapter(competicionesAdapter);
        });
    }

    // Recyclerview
    class PartidosAdapter extends RecyclerView.Adapter<PartidosViewHolder> {

        private List<Partidos> partidosList;

        @NonNull
        @Override
        public PartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PartidosViewHolder(ViewholderPartidosBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PartidosViewHolder holder, int position) {
            Partidos partidos = partidosList.get(position);
            holder.binding.equipoLocal.setText(partidos.equipoLocal);
            holder.binding.equipoVisitante.setText(partidos.equipoVisitante);
            holder.binding.horaInicioPartido.setText(partidos.horaInicio);
            holder.binding.minPartido.setText(partidos.minPartido);
            holder.binding.resultadoLocal.setText(partidos.resultadoLocal);
            holder.binding.resultadoVisitante.setText(partidos.resultadoVisitante);
        }

        @Override
        public int getItemCount() {
            if (partidosList == null) {
                return 0;
            } else {
                return partidosList.size();
            }
        }

        public void setPartidosList(List<Partidos> partidosList) {
            this.partidosList = partidosList;
            notifyDataSetChanged();
        }
    }


    class PartidosViewHolder extends RecyclerView.ViewHolder {
        ViewholderPartidosBinding binding;

        public PartidosViewHolder(ViewholderPartidosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



    class CompeticionesAdapter extends  RecyclerView.Adapter<CompeticionesViewHolder> {

        private List<Competiciones> competicionesList;

        @NonNull
        @Override
        public CompeticionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CompeticionesViewHolder(ViewholderCompeticionBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CompeticionesViewHolder holder, int position) {
            Competiciones competiciones = competicionesList.get(position);
            holder.binding.nombreCompeticion.setText(competiciones.nombre);
            holder.binding.iconoCompeticion.setImageDrawable(obtenerImagen(competicionesList.get(position)));
            holder.binding.linearLayoutCompeticion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PartidosAdapter partidosAdapter = new PartidosAdapter();
                    partidosViewModel.obtenerPartidos().observe(getViewLifecycleOwner(), listaPartidos -> {

                        // En el caso de que la base de datos no tenga datos insertados, se los metemos
                        if (listaPartidos.size() == 0) {
                            partidosViewModel.insertarDatosPartidos(new Partidos("21:00", "Real Madrid", "Inter", null, null, null));
                            partidosViewModel.insertarDatosPartidos(new Partidos("18:55", "FC Barcelona", "Dinamo Kiev", "85'", "3","1"));
                            partidosViewModel.insertarDatosPartidos(new Partidos("21:00", "Atalanta", "Liverpool", null, null, null));
                        }
                        partidosAdapter.setPartidosList(listaPartidos);
                        holder.binding.listaPartidos.setAdapter(partidosAdapter);
                    });

                    if (!partidosDesplegados) {
                        holder.binding.partidos.setVisibility(View.VISIBLE);
                        desplegarPartidos(holder.binding);
                        partidosDesplegados = true;
                    } else {
                        holder.binding.partidos.setVisibility(GONE);
                        recogerPartidos(holder.binding);
                        partidosDesplegados = false;
                    }
                }
            });

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
        ViewholderCompeticionBinding binding;

        public CompeticionesViewHolder(ViewholderCompeticionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }




    private void desplegarPartidos(ViewholderCompeticionBinding binding) {
        binding.buttonDesplegarPartidos.setVisibility(View.GONE);
        binding.buttonRecogerPartidos.setVisibility(View.VISIBLE);
    }

    private void recogerPartidos (ViewholderCompeticionBinding binding) {
        binding.buttonRecogerPartidos.setVisibility(GONE);
        binding.buttonDesplegarPartidos.setVisibility(View.VISIBLE);
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
