package com.example.proyectoapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoapp.Competiciones.Competicion;
import com.example.proyectoapp.Estadisticas.Estadisticas;
import com.example.proyectoapp.Jugadores.Jugador;
import com.example.proyectoapp.Partidos.Partido;
import com.example.proyectoapp.Login.User;

import java.util.List;


// Gestiona los datos y demas de la base de datos
@Dao
public interface DaoBaseDeDatos {
    // Busca el usuario y password
    @Query("SELECT * FROM User WHERE username = :username AND password = :password LIMIT 1")
    User obtenerUser(String username, String password);


    @Query("SELECT * FROM User")
    LiveData<List<User>> obtenerTodo();

    @Insert
    void insertar(User user);

    // Comprueba si existe algun usuario con ese nombre
    @Query("SELECT * FROM User where username = :username LIMIT 1")
    User comprobarUsuario(String username);

    // Cambia el usuario
    @Update
    void updateUsuario(User user);

    // Cambia la contraseña
    @Update
    void updatePassword(User user);

    // Elimina un usuario
    @Delete
    void eliminarUsuario(User user);



    // Insertar Partidos
    @Insert
    void insertarPartidos(Partido partido);

    // Comprueba si el partido con los siguientes datos existe
    @Query("SELECT * FROM Partido where equipoLocal = :equipoLocal AND equipoVisitante = :equipoVisitante AND horaInicio = :horaInicio AND minPartido = :minPartido AND resultadoLocal = :resultadoLocal AND resultadoVisitante = :resultadoVisitante AND competicion = :competicion AND  enVivo = :enVivo AND fecha = :fecha LIMIT 1")
    Partido comprobarPartido(String equipoLocal, String equipoVisitante, String horaInicio, String minPartido, String resultadoLocal, String resultadoVisitante, String competicion, boolean enVivo, String fecha);

    // Obtener lista de partidos
    @Query("SELECT * FROM Partido")
    LiveData<List<Partido>> obtenerPartidos();

    // Eliminar un partido
    @Delete
    void eliminarPartido(Partido partido);

    // Modificar partido
    @Update
    void modificarPartido(Partido partido);



    // Insertar competiciones
    @Insert
    void insertarCompeticiones(Competicion competicion);

    // Comprueba si la competicion existe
    @Query("SELECT * FROM Competicion where nombre = :nombre LIMIT 1")
    Competicion comprobarCompeticion(String nombre);

    // Obtener lista de competiciones
    @Query("SELECT * FROM Competicion")
    LiveData<List<Competicion>> obtenerCompeticiones();

    // Eliminar una competicion
    @Delete
    void eliminarCompeticion(Competicion competicion);

    //  Modificar competicion
    @Update
    void modificarCompeticion(Competicion competicion);



    // Insertar Estadisticas
    @Insert
    void insertarEstadisticas(Estadisticas estadisticas);

    // Comprueba si las estadisticas con los siguientes datos existen
    @Query("SELECT * FROM Estadisticas where porcentajeLocal = :porcentajeLocal AND porcentajeVisitante = :porcentajeVisitante AND tituloEstadistica = :tituloEstadistica LIMIT 1")
    Estadisticas comprobarEstadisticas(int porcentajeLocal, int porcentajeVisitante, String tituloEstadistica);

    // Obtener lista de estadisticas
    @Query("SELECT * FROM Estadisticas")
    LiveData<List<Estadisticas>> obtenerEstadisticas();

    // Eliminar un estadisticas
    @Delete
    void eliminarEstadisticas(Estadisticas estadisticas);

    // Modificar estadisticas
    @Update
    void modificarEstadisticas(Estadisticas estadisticas);



    // Insertar Jugador
    @Insert
    void insertarJugadores(Jugador jugador);

    // Comprueba si los jugadores con los siguientes datos existen
    @Query("SELECT * FROM Jugador where nombre = :nombre AND pais = :pais AND tarjetaAmarilla = :tarjetaAmarilla AND tarjetaRoja = :tarjetaRoja AND gol = :gol AND cambio = :cambio AND onceInicial = :onceInicial LIMIT 1")
    Jugador comprobarJugador(String nombre, String pais, boolean tarjetaAmarilla, boolean tarjetaRoja, int gol, boolean cambio, boolean onceInicial);

    // Obtener lista de jugadores
    @Query("SELECT * FROM Jugador WHERE equipo = :equipo AND onceInicial = :onceInicial")
    LiveData<List<Jugador>> obtenerJugadoresEquipo(String equipo, boolean onceInicial);

    @Query("DELETE FROM jugador")
    void eliminarJugadores();


}

