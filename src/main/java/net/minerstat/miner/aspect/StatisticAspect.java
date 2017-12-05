package net.minerstat.miner.aspect;

import net.minerstat.miner.dao.WorkerDao;
import net.minerstat.miner.entity.Worker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class StatisticAspect {

    @Autowired
    private WorkerDao workerDAO;

    @AfterReturning(pointcut="execution(net.minerstat.miner.entity.Worker net.minerstat.miner.service.statistic.Algorithm.parseAlgorithm(..))", returning="worker")
    public void saveDataAfterParser(JoinPoint jp, Worker worker){
        workerDAO.saveWorker(worker);
    }
}
