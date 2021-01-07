package com.example.proyectoapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoapp.Eventos.Partidos;
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
    @Query("SELECT * FROM Partidos where equipoLocal = :equipoLocal AND equipoVisitante = :equipoVisitante AND horaInicio = :horaInicio")
    Partidos comprobarPartido(String equipoLocal, String equipoVisitante, String horaInicio);
    // Obtener lista de partidos
    LiveData<List<Partidos>> obtenerPartidos(String equipoLocal, String equipoVisitante, String horaInicio);



}

