package net.minerstat.miner.dao;

import java.util.List;

import net.minerstat.miner.entity.User;

public interface UserDao {

    void insertUser(User user);

    void updateUser(User user);

    boolean deleteUser(Long uid);

    User getUser(int uid);

    List<User> findAll();
}
