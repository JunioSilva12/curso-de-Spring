package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // de esta formas el spring boot sabra que esta clase es un controlador
public class UserControllers
{
    //este controlador servira para manejar las rutas que pide el usuario
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtytil;

 /*   @RequestMapping(value = "user/{id}")
    public User getUser(@PathVariable Long id) {
        User usuario = new User();
        usuario.setId(id);
        usuario.setName("Kazuto000");
        usuario.setLastname("Kirigaya");
        usuario.setEmail("kirito@email.com");
        usuario.setNumber_phone("32134764");
        //usuario.setPassword("2354352");
        return usuario;
    }*/
    private boolean authenticateToken(String token){
            if (!jwtytil.validateJWT(token) )  return false;
            String idUser =  jwtytil.getKey(token);  
            if (idUser != null)   return true;   
            return false;
        }

    @RequestMapping(value = "api/users")
    public List<User>  getUsers(@RequestHeader(value = "Authorization") String token) {
      //  System.out.println("EL TOKEN ES : ." + token);      
        if (token == "")   return new ArrayList<>();//retorna una lista vacia   
        if(!authenticateToken(token))  return new ArrayList<>();//retorna una lista vacia  
        return userDao.getUsers() ;
      
    }

   



    @RequestMapping(value = "api/users" , method=RequestMethod.POST)
    public void  setUser( @RequestBody User usuario)
    {      
        
        Argon2 a2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); // creamos un objeto de la libreria 
        //no investigare esta libreria a profundidad solo me importa que funcione;

        String hash = a2.hash(1,1024,1,usuario.getPassword());//mientras mas iteraciones tenga mas encriptaciones hace(mas seguro ),el paralelismo es que solo se encripte la misma contraseña una vez
         usuario.setPassword(hash);
        userDao.registerUser(usuario) ;
    }


  /*  @RequestMapping(value = "user1")
    public User editUser() {
        User usuario = new User();
        usuario.setName("Kazuto1");
        usuario.setLastname("Kirigaya");
        usuario.setEmail("kirito@email.com");
        usuario.setNumber_phone("32134764");
        //usuario.setPassword("2354352");
        return usuario;
    }
*/
    @RequestMapping(value = "api/users/{id}" , method = RequestMethod.DELETE)//esto no indica que elimine
    public void delete(@RequestHeader(value = "Authorization") String token ,@PathVariable long id) {//la eliminacion se hará en esta funcion
        if (token == "")   return ;//retorna   
        if(!authenticateToken(token))  return;//retorna 
        userDao.delete(id) ;
    }
/*
    @RequestMapping(value = "user3")
    public User searchUser() {
        User usuario = new User();
        usuario.setName("Kazuto3");
        usuario.setLastname("Kirigaya");
        usuario.setEmail("kirito@email.com");
        usuario.setNumber_phone("32134764");
        //usuario.setPassword("2354352");
        return usuario;
    }*/


}
