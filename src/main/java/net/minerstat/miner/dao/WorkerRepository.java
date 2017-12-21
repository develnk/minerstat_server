package net.minerstat.miner.dao;

import net.minerstat.miner.entity.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Long> {

    @Query("select w from Worker w where worker_id = ?1")
    Worker findByWorkerId(String workerId);

}
