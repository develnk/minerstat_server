package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.RoleRepository;
import net.minerstat.miner.dao.WorkerRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository("workerDao")
public class WorkerDAOImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private WorkerRepository workerRepository;

    public void insertWorker(Worker worker) {
        em.persist(worker);
    }

}
