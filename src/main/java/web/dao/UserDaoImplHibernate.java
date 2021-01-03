package web.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import web.model.User;


import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImplHibernate implements UserDao {

    @Autowired
    EntityManagerFactory emf;

    @Override
    public void createUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public User readUser(Long idUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, idUser);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public void updateUser(Long idUser,String name, String lastname, int age) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, idUser);
        user.setName(name);
        user.setLastName(lastname);
        user.setAge(age);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteUser(Long idUser) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(User.class, idUser));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> getUsers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query from_user = em.createQuery("select u from User u");
        List<User> resultList = from_user.getResultList();
        em.getTransaction().commit();
        em.close();
        return resultList;
    }
}