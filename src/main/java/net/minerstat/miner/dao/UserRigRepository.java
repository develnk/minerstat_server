package net.minerstat.miner.dao;

import net.minerstat.miner.entity.UserRig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRigRepository  extends CrudRepository<UserRig, Long> {

    @Query("select uid from UserRig where rig_id = ?1")
    Long findUidByRigId(String rigId);

    @Query("select u from UserRig u where rig_id = ?1")
    UserRig findByRigId(String rigId);
}
