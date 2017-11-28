package net.minerstat.miner.service;

import net.minerstat.miner.entity.User;

import java.util.List;

public interface UserService {
    List<User> fetchAll();
    User findByEmail(String email);
}
