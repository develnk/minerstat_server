package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.UsersRigDao;
import net.minerstat.miner.dao.UsersRigRepository;
import net.minerstat.miner.entity.UsersRig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("UsersRigDao")
@Transactional
public class UsersRigDaoImpl implements UsersRigDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsersRigRepository usersRigRepository;

    public void insertUsersRig(UsersRig userRig) {
        em.persist(userRig);
    }

    @Transactional(readOnly=true)
    public UsersRig usersRig(int id) {
        return em.find(UsersRig.class, id);
    }

    @Transactional(readOnly=true)
    public UsersRig findByRigId(String rigId) {
        return usersRigRepository.findByRigId(rigId);
    }

}
