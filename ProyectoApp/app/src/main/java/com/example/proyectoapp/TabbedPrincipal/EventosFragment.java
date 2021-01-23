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
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentEventosBinding;
import com.example.proyectoapp.databinding.ViewholderCompeticionBinding;
import com.example.proyectoapp.databinding.ViewholderPartidosBinding;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;

public class EventosFragment extends Fragment {
    FragmentEventosBinding binding;
    ViewholderCompeticionBinding viewholderCompeticionBinding;
    private PartidosViewModel partidosViewModel;
    private CompeticionesViewModel competicionesViewModel;
    private NavController navController;



    boolean partidosDesplegados = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentEventosBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);
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
            binding.listaCompeticiones.setAdapter(competicionesAdapter);
        });
    }

    // Recyclerview
    public class PartidosAdapter extends RecyclerView.Adapter<PartidosViewHolder> {

        private List<Partido> partidoList;

        @NonNull
        @Override
        public PartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PartidosViewHolder(ViewholderPartidosBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PartidosViewHolder holder, int position) {
            Partido partido = partidoList.get(position);
            holder.binding.equipoLocal.setText(partido.equipoLocal);
            holder.binding.equipoVisitante.setText(partido.equipoVisitante);
            holder.binding.horaInicioPartido.setText(partido.horaInicio);
            holder.binding.minPartido.setText(partido.minPartido);
            holder.binding.resultadoLocal.setText(partido.resultadoLocal);
            holder.binding.resultadoVisitante.setText(partido.resultadoVisitante);

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    partidosViewModel.seleccionar(partido);
                    navController.navigate(R.id.go_to_detallesPartido);
                }
            });

        }

        @Override
        public int getItemCount() {
            if (partidoList == null) {
                return 0;
            } else {
                return partidoList.size();
            }
        }

        public void setPartidoList(List<Partido> partidoList) {
            this.partidoList = partidoList;
            notifyDataSetChanged();
        }
    }


    public class PartidosViewHolder extends RecyclerView.ViewHolder {
        ViewholderPartidosBinding binding;

        public PartidosViewHolder(ViewholderPartidosBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }



    public class CompeticionesAdapter extends  RecyclerView.Adapter<CompeticionesViewHolder> {

        private List<Competicion> competicionList;


        @NonNull
        @Override
        public CompeticionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CompeticionesViewHolder(ViewholderCompeticionBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CompeticionesViewHolder holder, int position) {

            Competicion competicion = competicionList.get(position);
            holder.binding.nombreCompeticion.setText(competicion.nombre);
            holder.binding.iconoCompeticion.setImageDrawable(obtenerIconoCompeticion(competicionList.get(position)));
            holder.binding.linearLayoutCompeticion.setOnClickListener(view -> {

                PartidosAdapter partidosAdapter = new PartidosAdapter();
                partidosViewModel.obtenerPartidos().observe(getViewLifecycleOwner(), listaPartidos -> {

                    // En el caso de que la base de datos no tenga datos insertados, se los metemos
                    if (listaPartidos.size() == 0) {
                        partidosViewModel.insertarDatosPartidos(new Partido("21:00", "Real Madrid", "Inter", null, null, null,"Champions League", false, "16.01.2021"));
                        partidosViewModel.insertarDatosPartidos(new Partido("18:55", "FC Barcelona", "Dinamo Kiev", "85'", "3","1", "Champions League", true, "17.01.2021"));
                        partidosViewModel.insertarDatosPartidos(new Partido("21:00", "Atalanta", "Liverpool", null, null, null, "Champions League", false, "18.01.2021"));
                    }
                    partidosAdapter.setPartidoList(listaPartidos);
                    holder.binding.listaPartidos.setAdapter(partidosAdapter);

                });


                if (!partidosDesplegados) {
                    holder.binding.partido.setVisibility(View.VISIBLE);
                    desplegarPartidos(holder.binding);
                    partidosDesplegados = true;
                } else {
                    holder.binding.partido.setVisibility(GONE);
                    recogerPartidos(holder.binding);
                    partidosDesplegados = false;
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

    public class  CompeticionesViewHolder extends RecyclerView.ViewHolder {
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





    private Drawable obtenerIconoCompeticion(Competicion competicion) {
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
