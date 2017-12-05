package net.minerstat.miner.dao;

import net.minerstat.miner.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Long findRidByName(String name);

}
