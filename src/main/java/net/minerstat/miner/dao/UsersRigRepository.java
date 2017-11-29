package net.minerstat.miner.dao;

import net.minerstat.miner.entity.UsersRig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRigRepository extends CrudRepository<UsersRig, Long> {

    @Query("select u from UsersRig u where rig_id = ?1")
    UsersRig findByRigId(String rigId);
}
