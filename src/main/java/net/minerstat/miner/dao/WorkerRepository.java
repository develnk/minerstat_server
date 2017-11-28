package net.minerstat.miner.dao;

import net.minerstat.miner.entity.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Long> {

}
