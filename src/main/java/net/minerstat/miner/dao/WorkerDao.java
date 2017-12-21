package net.minerstat.miner.dao;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UsersRig;
import net.minerstat.miner.entity.Worker;

public interface WorkerDao {

    Worker saveWorker(Worker worker);

    Worker findByWorkerId(String workerId);
}
