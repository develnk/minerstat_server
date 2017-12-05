package net.minerstat.miner.dao;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.Worker;

public interface WorkerDao {

    Worker getWorkerByToken(String token);

    Worker saveWorker(Worker worker);

    User getUserByWorkerToken(String token);

}
