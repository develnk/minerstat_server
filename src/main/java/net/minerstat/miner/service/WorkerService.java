package net.minerstat.miner.service;

import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.enums.MinerTypes;

public interface WorkerService {
    Worker newWorker(String email, String password,  MinerTypes minerType, String rigId);


}
