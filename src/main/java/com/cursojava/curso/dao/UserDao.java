package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;

import java.util.List;

//la carpeta dao hace referencia a Data Access Object
// en pocas palabras es donde ira todas las clases que haran la coneccion al la base de datos
//solo por este curso usaremos mysql en  phpadmin
public interface UserDao {

// resumiendo una interfase indica o Obliga que funciones debe tener una clase
public List<User> getUsers();
public void delete(Long id);
public void registerUser(User user);
public User getUserWithCredentials(User user);
}
