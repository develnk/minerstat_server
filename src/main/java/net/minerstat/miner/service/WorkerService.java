package net.minerstat.miner.service;

import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.request.WorkerStatRequest;

import java.util.List;

public interface WorkerService {

    Worker newWorker(WorkerRequest workerRequest);

    Boolean saveStat(WorkerStatRequest workerStatRequest);

    List<Worker> findUserWorkers();
}
