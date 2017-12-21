package net.minerstat.miner.dao;

import net.minerstat.miner.entity.User;

import java.util.List;

public interface UserDao {

    void insertUser(User user);

    void updateUser(User user);

    boolean deleteUser(Long uid);

    User getUser(int uid);

    User findByEmail(String email);

    List<User> findAll();
}
