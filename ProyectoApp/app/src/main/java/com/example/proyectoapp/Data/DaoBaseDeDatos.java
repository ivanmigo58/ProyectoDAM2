package com.example.proyectoapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoapp.Competiciones.Competiciones;
import com.example.proyectoapp.Partidos.Partidos;
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
    void insertarPartidos(Partidos partidos);

    // Comprueba si el partido con los siguientes datos existe
    @Query("SELECT * FROM Partidos where equipoLocal = :equipoLocal AND equipoVisitante = :equipoVisitante AND horaInicio = :horaInicio AND minPartido = :minPartido AND resultadoLocal = :resultadoLocal AND resultadoVisitante = :resultadoVisitante LIMIT 1")
    Partidos comprobarPartido(String equipoLocal, String equipoVisitante, String horaInicio, String minPartido, String resultadoLocal, String resultadoVisitante);

    // Obtener lista de partidos
    @Query("SELECT * FROM Partidos")
    LiveData<List<Partidos>> obtenerPartidos();

    // Eliminar un partido
    @Delete
    void eliminarPartido(Partidos partidos);

    // Modificar partido
    @Update
    void modificarPartido(Partidos partidos);



    // Insertar competiciones
    @Insert
    void insertarCompeticiones(Competiciones competiciones);

    // Comprueba si la competicion existe
    @Query("SELECT * FROM Competiciones where nombre = :nombre LIMIT 1")
    Competiciones comprobarCompeticion(String nombre);

    // Obtener lista de competiciones
    @Query("SELECT * FROM Competiciones")
    LiveData<List<Competiciones>> obtenerCompeticiones();

    // Eliminar una competicion
    @Delete
    void eliminarCompeticion(Competiciones competiciones);

    //  Modificar competicion
    @Update
    void modificarCompeticion(Competiciones competiciones);



}

