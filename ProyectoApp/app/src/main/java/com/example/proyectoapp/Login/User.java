package com.example.proyectoapp.Login;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String username;
    public String password;

    // Constructor para room
    public User() {

    }

    // Constructor
    public User(String nombre, String password) {
        this.username = nombre;
        this.password = password;
    }
}

