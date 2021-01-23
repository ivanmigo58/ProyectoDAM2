package com.example.proyectoapp.TabbedPartidos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectoapp.Estadisticas.Estadisticas;
import com.example.proyectoapp.Estadisticas.EstadisticasViewModel;
import com.example.proyectoapp.databinding.FragmentEstadisticasBinding;
import com.example.proyectoapp.databinding.ViewholderListaestadisticasBinding;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EstadisticasFragment extends Fragment {
    FragmentEstadisticasBinding binding;
    private EstadisticasViewModel estadisticasViewModel;
    private ViewholderListaestadisticasBinding viewholderListaestadisticasBinding;
    private Handler handler = new Handler();
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentEstadisticasBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Estadisticas estadisticas = null;

        estadisticasViewModel = new ViewModelProvider(requireActivity()).get(EstadisticasViewModel.class);

        EstadisticasAdapter estadisticasAdapter = new EstadisticasAdapter();

        estadisticasViewModel.obtenerEstadisticas().observe(getViewLifecycleOwner(), listaEstadisitica -> {

            // En el caso de que la base de datos no tenga datos insertados, se los metemos
            if (listaEstadisitica.size() == 0) {
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(57,42,"Posesión de balón"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(22,8,"Remates"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(14,7,"Remates a puerta"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(5,1,"Remates fuera"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(3,0,"Remates rechazados"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(3,12,"Tiros libres"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(10,8,"Córners"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(2,0,"Fueras de juego"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(10,8,"Saques de banda"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(6,12,"Paradas"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(10,4,"Faltas"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(0,1,"Tarjetas amarillas"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(0,1,"Tarjetas rojas"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(771,448,"Pases totales"));
                estadisticasViewModel.insertarDatosEstadistica(new Estadisticas(722,406,"Pases completados"));

            }
            estadisticasAdapter.setEstadisticasList(listaEstadisitica);
            binding.listaEstadisticas.setAdapter(estadisticasAdapter);

        });


    }



    public class EstadisticasAdapter extends RecyclerView.Adapter<EstadisticasViewHolder> {
        private List<Estadisticas> estadisticasList;

        @NonNull
        @Override
        public EstadisticasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EstadisticasViewHolder(ViewholderListaestadisticasBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull EstadisticasViewHolder holder, int position) {
            Estadisticas estadisticas = estadisticasList.get(position);
            if (estadisticas.tituloEstadistica.equals("Posesión de balón")) {
                holder.binding.porcentajeLocal.setText( String.valueOf(estadisticas.porcentajeLocal) + "%");
                holder.binding.porcentajeVisitante.setText(String.valueOf(estadisticas.porcentajeVisitante) + "%");
            } else {
                holder.binding.porcentajeLocal.setText( String.valueOf(estadisticas.porcentajeLocal));
                holder.binding.porcentajeVisitante.setText(String.valueOf(estadisticas.porcentajeVisitante));
            }
            holder.binding.tituloEstadistica.setText(estadisticas.tituloEstadistica);


            int total = estadisticas.porcentajeLocal + estadisticas.porcentajeVisitante;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    holder.binding.progressHorizontaLocal.setRotation(180);
                    holder.binding.progressHorizontaLocal.setProgress(calculoPorcentaje(total, estadisticas.porcentajeLocal));
                    holder.binding.progressHorizontaVisitante.setProgress(calculoPorcentaje(total, estadisticas.porcentajeVisitante));
                }
            });

        }

        @Override
        public int getItemCount() {
            if (estadisticasList == null) {
                return 0;
            } else {
                return estadisticasList.size();
            }
        }

        public void setEstadisticasList(List<Estadisticas> estadisticasList) {
            this.estadisticasList = estadisticasList;
            notifyDataSetChanged();
        }
    }



    public class EstadisticasViewHolder extends RecyclerView.ViewHolder {
        ViewholderListaestadisticasBinding binding;

        public EstadisticasViewHolder(ViewholderListaestadisticasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    private int calculoPorcentaje(int total, int porcentaje) {

        int enteroLocal = porcentaje * 100;
        double porcentajeLocalDecimal = enteroLocal / total;
        return Math.toIntExact(Math.round(porcentajeLocalDecimal));
    }

}
