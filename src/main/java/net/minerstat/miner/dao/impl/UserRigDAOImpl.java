package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.RoleRepository;
import net.minerstat.miner.dao.UserRigRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UserRig;
import net.minerstat.miner.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("userRigDao")
@Transactional
public class UserRigDAOImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRigRepository userRigRepository;

    public void insertUserRig(UserRig userRig) {
        em.persist(userRig);
    }

    @Transactional(readOnly=true)
    public Long findUidByRigId(String rigId) {
        return userRigRepository.findUidByRigId(rigId);
    }

    @Transactional(readOnly=true)
    public UserRig userRig(int id) {
        return em.find(UserRig.class, id);
    }

    public UserRig findByRigId(String rigId) {
        return userRigRepository.findByRigId(rigId);
    }

}
