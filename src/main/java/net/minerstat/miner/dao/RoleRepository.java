package net.minerstat.miner.dao;

import net.minerstat.miner.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select id from Role where name = ?1 ORDER BY id ASC")
    Long findRidByName(String name);
}
