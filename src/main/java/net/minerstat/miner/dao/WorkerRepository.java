package net.minerstat.miner.dao;

import net.minerstat.miner.entity.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Long> {

    @Query("select w from Worker w where worker_id = ?1")
    Worker findByWorkerId(String workerId);

    @Query("select w from Worker w where user_rig_id IN (select id from UsersRig where uid = ?1)")
    List<Worker> findAllWorkersUser(long uid);

}
