package net.minerstat.miner.dao;

import net.minerstat.miner.entity.UsersRig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRigRepository extends CrudRepository<UsersRig, Long> {

    @Query("select u from UsersRig u where rig_id = ?1")
    UsersRig findByRigId(String rigId);

    @Query("select u from UsersRig u where uid = ?1")
    List<UsersRig> findAllRigsUser(long uid);
}
