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

    public Worker getWorkerByToken(String token) {
        return workerRepository.findByToken(token);
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public User getUserByWorkerToken(String token) {
        Worker worker = getWorkerByToken(token);
        return worker.getUsersRig().getUser();
    }

}
