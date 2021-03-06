package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.UserDao;
import net.minerstat.miner.entity.User;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertUser(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User attached = em.find(User.class, user.getUid());
        if(attached==null) {
            throw new EntityExistsException("User Not found.");
        }

        user.setUpdated(new Date());
        em.merge(user);
    }

    @Override
    public boolean deleteUser(Long uid) {
        User attached = em.find(User.class, uid);
        em.remove(attached);
        return true;
    }

    @Override
    public User getUser(int uid) {
        return em.find(User.class, uid);
    }

    @Override
    public User findByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    public List<User> findAll() {
        return em.createQuery("SELECT * FROM users").getResultList();
    }
}
