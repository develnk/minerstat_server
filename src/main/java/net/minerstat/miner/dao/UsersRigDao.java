package net.minerstat.miner.dao;

import net.minerstat.miner.entity.UsersRig;

import java.util.List;

public interface UsersRigDao {

    void insertUsersRig(UsersRig userRig);

    UsersRig usersRig(int id);

    UsersRig findByRigId(String rigId);

}
