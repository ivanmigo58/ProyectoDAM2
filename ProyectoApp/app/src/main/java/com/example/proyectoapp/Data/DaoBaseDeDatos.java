package com.example.proyectoapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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



}

