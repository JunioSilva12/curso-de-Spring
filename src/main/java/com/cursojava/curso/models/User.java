package com.cursojava.curso.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity //hace referencia que esta clase es una entidad
@Table(name = "usuarios")//con esto sabe la tabla de la bd
public class User
{
//hibernate no se da cuenta de que columnas pertenece a cada atributo para eso usamos la anotacion column
    @Getter @Setter 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//de esta forma se auto completa el id a la hora de adduser
    private  Long id;
    @Getter @Setter @Column(name = "name")
    private  String name;
    @Getter @Setter @Column(name = "last_name")
    private  String lastname;
    @Getter @Setter@ Column(name = "email")
    private  String email;
    @Getter @Setter @Column(name = "n_phone")
    private  String number_phone;
    @Getter @Setter @Column(name = "password")
    private  String password;

    //agregando la dependencia Maven.lombok solo con las anotaciones tendriamos los setters y getters


}
