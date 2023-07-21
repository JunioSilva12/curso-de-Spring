package com.cursojava.curso.dao;

import com.cursojava.curso.models.User;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional//que pendejada no me dejaba y solo tenia que recargar pon.xml con maven
public class UserDaoImp implements  UserDao
{
    @PersistenceContext
    private EntityManager entityManager;
    //el entitymanager nos servira para hacer la coneccion con la bAAse de datos
    @Override
    @Transactional
    @SuppressWarnings("unchecked")//IMPORTANTISIMO
    public List<User> getUsers()
    {
        String query = "FROM User"; // esta query es de la clase user
        //entonces la clase user debe indicar que tabla tiene los datos
        List<User> listRes = entityManager.createQuery(query).getResultList(); //creamos la consulta y obtenemos el resultado
        return listRes;
    }

    @Override
    public void delete(Long id) {
    User usuario = entityManager.find(User.class , id) ; //aca consultamos un usuario a eliminar
    entityManager.remove(usuario);
    }

    @Override
    public void registerUser(User user) {
    entityManager.merge(user) ;//con esto ya ingresa a la bd   sirve para crear cuaves filas(usuarios) y actualizarlas
   //pero no te autocompleta 
   //para autocompletar el ID se debe poner una anotacion especial 
}

    @Override
    @SuppressWarnings("unchecked")
    public User getUserWithCredentials(User user) {
        String query = "FROM User WHERE email = :email "; // esta query es de la clase user
        //entonces la clase user debe indicar que tabla tiene los datos
        System.out.println(user.toString());
        List<User> listRes = entityManager.createQuery(query)
                            .setParameter("email",user.getEmail())                
                            .getResultList(); //creamos la consulta y obtenemos el resultado
       // return listRes;
       if (listRes.isEmpty()) return null;
              
        String passwordHashed =listRes.get(0).getPassword();
        Argon2 a2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); 
        if( a2.verify(passwordHashed, user.getPassword()))
        {
            return listRes.get(0);
        };
        return null;
       
       //return listRes.isEmpty() ? false:true ;
         
    }

   


}
