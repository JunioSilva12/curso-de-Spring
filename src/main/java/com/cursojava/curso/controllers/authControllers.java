package com.cursojava.curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;

@RestController
public class authControllers {
    @Autowired
    private UserDao userDao;
   @Autowired
   private JWTUtil jwtytil;
    @RequestMapping(value = "api/login" , method=RequestMethod.POST)
    public String authenticateUser( @RequestBody User usuario)
    {       
        User searchedUser =userDao.getUserWithCredentials(usuario) ;
        if (searchedUser == null) return "FAIL";
        String tokenJWT = jwtytil.create(String.valueOf(searchedUser.getId()), searchedUser.getEmail() );
        System.out.println("es aqui" + tokenJWT);
        return tokenJWT;
      //  return userDao.verificateCredentials(usuario) ? "OK" : "FAIL";         
    }




}
