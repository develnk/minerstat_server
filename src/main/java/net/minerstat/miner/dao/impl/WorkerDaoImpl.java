package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.WorkerDao;
import net.minerstat.miner.dao.WorkerRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("workerDao")
public class WorkerDaoImpl implements WorkerDao {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker findByWorkerId(String workerId) {
        return workerRepository.findByWorkerId(workerId);
    }

}
