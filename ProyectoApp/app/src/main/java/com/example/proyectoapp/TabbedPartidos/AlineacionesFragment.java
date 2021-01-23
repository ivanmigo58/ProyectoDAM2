package com.example.proyectoapp.TabbedPartidos;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Jugadores.Jugador;
import com.example.proyectoapp.Jugadores.JugadorViewModel;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Partidos.PartidosViewModel;
import com.example.proyectoapp.R;
import com.example.proyectoapp.databinding.FragmentAlineacionesBinding;
import com.example.proyectoapp.databinding.ViewholderJugadoreslocalesBinding;
import com.example.proyectoapp.databinding.ViewholderJugadoresvisitantesBinding;

import java.util.List;


public class AlineacionesFragment extends Fragment {
    FragmentAlineacionesBinding binding;
    private JugadorViewModel jugadorViewModel;
    private PartidosViewModel partidosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentAlineacionesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jugadorViewModel = new ViewModelProvider(requireActivity()).get(JugadorViewModel.class);
        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);


        JugadorAdapter jugadorAdapter = new JugadorAdapter();
        JugadorVisitanteAdapter jugadorVisitanteAdapter = new JugadorVisitanteAdapter();
        JugadorLocalSuplenteAdapter jugadorLocalSuplenteAdapter = new JugadorLocalSuplenteAdapter();
        Partido partido = partidosViewModel.seleccionado().getValue();

        jugadorViewModel.eliminarJugador();

        jugadorViewModel.obtenerJugadores(partido.equipoLocal, true).observe(getViewLifecycleOwner(), listaJugadores ->{
            if (listaJugadores.size() == 0) {
                jugadorViewModel.insertarDatosJugador(new Jugador("Ter Stegen", "Alemania", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Dest", "EEUU", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Pique", "España", false, false,1,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("De Jong", "Holanda", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Alba", "España", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Busquuets", "España", false, false,0,true,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Messi", "Argentina", false, false,2,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Pjanic", "Bosnia y Herzegobina", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Pedri", "España", false, false,0,true,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Griezmann", "Francia", false, false,0,false,true,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Ansu Fati", "España", false, false,0,false,true,"FC Barcelona"));
            }
            jugadorAdapter.setJugadorList(listaJugadores);
            binding.listaJugadoresLocales.setAdapter(jugadorAdapter);
        });

        jugadorViewModel.obtenerJugadores(partido.equipoVisitante, true).observe(getViewLifecycleOwner(), listaJugadoresVisitantes -> {
            if (listaJugadoresVisitantes.size() == 0) {
                jugadorViewModel.insertarDatosJugador(new Jugador("Nescheret", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Kedziora", "Ucrania", false, true,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Zabarnyi", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Poopov", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Shabanov", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Buyalskyy", "Ucrania", true, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Shepelev", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Andrievsky", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Tsygankov", "Ucrania", false, false,1,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Supriaga", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Rodrigues", "Ucrania", false, false,0,false,true,"Dinamo Kiev"));
            }
            jugadorVisitanteAdapter.setJugadorList(listaJugadoresVisitantes);
            binding.listaJugadoresVisitantes.setAdapter(jugadorVisitanteAdapter);
        });


        jugadorViewModel.obtenerJugadores(partido.equipoLocal, false).observe(getViewLifecycleOwner(), listaJugadoresLocalesSuplentes -> {
            if (listaJugadoresLocalesSuplentes.size() == 0 ) {
                jugadorViewModel.insertarDatosJugador(new Jugador("C. Aleña", "España", false, false,0,true,false,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("M. Braithwaite", "Dinamarca", false, false,0,false,false,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("O. Dembélé", "Francia", false, false,0,false,false,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("J. Firpo", "España", false, false,0,false,false,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("C. Lenglet", "Francia", false, false,0,true,false,"FC Barcelona"));
                jugadorViewModel.insertarDatosJugador(new Jugador("Neto", "Brasil", false, false,0,false,false,"FC Barcelona"));
            }
            jugadorLocalSuplenteAdapter.setJugadorList(listaJugadoresLocalesSuplentes);
            binding.listaJugadoresLocalesSuplentes.setAdapter(jugadorLocalSuplenteAdapter);
        });




    }

    class JugadorAdapter extends RecyclerView.Adapter<JugadoresViewHolder> {

        private List<Jugador> jugadorList;

        @NonNull
        @Override
        public JugadoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadoresViewHolder(ViewholderJugadoreslocalesBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JugadoresViewHolder holder, int position) {
            Jugador jugador = jugadorList.get(position);
                holder.binding.nombreJugador.setText(jugador.nombre);
                holder.binding.nacionalidadJugador.setImageDrawable(obtenerNacionalidad(jugadorList.get(position)));
                holder.binding.accionJugadorLocal.setImageDrawable(obtenerAccionJugador(jugadorList.get(position)));

        }

        @Override
        public int getItemCount() {
            if (jugadorList == null) {
                return 0;
            } else {
                return jugadorList.size();
            }
        }

        public void setJugadorList(List<Jugador> jugadorList) {
            this.jugadorList = jugadorList;
            notifyDataSetChanged();
        }
    }

    class JugadoresViewHolder extends RecyclerView.ViewHolder {
        ViewholderJugadoreslocalesBinding binding;

        public JugadoresViewHolder(ViewholderJugadoreslocalesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }




    class JugadorLocalSuplenteAdapter extends RecyclerView.Adapter<JugadoresLocalSuplentesViewHolder> {

        private List<Jugador> jugadorList;

        @NonNull
        @Override
        public JugadoresLocalSuplentesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadoresLocalSuplentesViewHolder (ViewholderJugadoreslocalesBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JugadoresLocalSuplentesViewHolder holder, int position) {
            Jugador jugador = jugadorList.get(position);
                holder.binding.nombreJugador.setText(jugador.nombre);
                holder.binding.nacionalidadJugador.setImageDrawable(obtenerNacionalidad(jugadorList.get(position)));
                holder.binding.accionJugadorLocal.setImageDrawable(obtenerAccionJugador(jugadorList.get(position)));

        }

        @Override
        public int getItemCount() {
            if (jugadorList == null) {
                return 0;
            } else {
                return jugadorList.size();
            }
        }

        public void setJugadorList(List<Jugador> jugadorList) {
            this.jugadorList = jugadorList;
            notifyDataSetChanged();
        }
    }



    class JugadoresLocalSuplentesViewHolder extends RecyclerView.ViewHolder {
        ViewholderJugadoreslocalesBinding binding;

        public JugadoresLocalSuplentesViewHolder(ViewholderJugadoreslocalesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }






    class JugadorVisitanteAdapter extends RecyclerView.Adapter<JugadoresVisitantesViewHolder> {

        private List<Jugador> jugadorVisitanteList;

        @NonNull
        @Override
        public JugadoresVisitantesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JugadoresVisitantesViewHolder(ViewholderJugadoresvisitantesBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JugadoresVisitantesViewHolder holder, int position) {
            Jugador jugador = jugadorVisitanteList.get(position);
         //   if (jugador.equipo.equals("Dinamo Kiev")) {
                holder.binding.nombreJugador.setText(jugador.nombre);
                holder.binding.nacionalidadJugador.setImageDrawable(obtenerNacionalidad(jugadorVisitanteList.get(position)));
                holder.binding.accionJugadorLocal.setImageDrawable(obtenerAccionJugador(jugadorVisitanteList.get(position)));
         //   }
        }

        @Override
        public int getItemCount() {
            if (jugadorVisitanteList == null) {
                return 0;
            } else {
                return jugadorVisitanteList.size();
            }
        }

        public void setJugadorList(List<Jugador> jugadorList) {
            this.jugadorVisitanteList = jugadorList;
            notifyDataSetChanged();
        }
    }

    class JugadoresVisitantesViewHolder extends RecyclerView.ViewHolder {
        ViewholderJugadoresvisitantesBinding binding;

        public JugadoresVisitantesViewHolder(ViewholderJugadoresvisitantesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    private Drawable obtenerNacionalidad(Jugador jugador) {
        Drawable pais = null;
        if (jugador.pais.equals("España")) {
            pais = getResources().getDrawable(R.drawable.espana);
        } else if (jugador.pais.equals("Francia")) {
            pais = getResources().getDrawable(R.drawable.francia);
        } else if (jugador.pais.equals("Holanda")) {
            pais = getResources().getDrawable(R.drawable.holanda);
        } else if (jugador.pais.equals("Argentina")) {
            pais = getResources().getDrawable(R.drawable.argentina);
        } else if (jugador.pais.equals("EEUU")) {
            pais = getResources().getDrawable(R.drawable.eeuu);
        } else if (jugador.pais.equals("Bosnia y Herzegobina")) {
            pais = getResources().getDrawable(R.drawable.bosnia);
        } else if (jugador.pais.equals("Alemania")) {
            pais = getResources().getDrawable(R.drawable.alemania);
        } else if (jugador.pais.equals("Ucrania")) {
            pais = getResources().getDrawable(R.drawable.ucrania);
        }
        return pais;
    }

    private Drawable obtenerAccionJugador(Jugador jugador) {
        Drawable accion = null;
        if (jugador.cambio == true) {
            accion = getResources().getDrawable(R.drawable.cambio_jugador);
        } else if (jugador.tarjetaAmarilla == true) {
            accion = getResources().getDrawable(R.drawable.targeta_amarilla);
        } else if (jugador.tarjetaRoja == true) {
            accion = getResources().getDrawable(R.drawable.targeta_roja);
        } else if (jugador.gol > 0) {
            accion = getResources().getDrawable(R.drawable.gol);
        }
        return accion;
    }
}