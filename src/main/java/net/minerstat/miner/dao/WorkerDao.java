package net.minerstat.miner.dao;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UsersRig;
import net.minerstat.miner.entity.Worker;

import java.util.List;

public interface WorkerDao {

    Worker saveWorker(Worker worker);

    Worker findByWorkerId(String workerId);

    List<Worker> userWorkers(long uid);
}
