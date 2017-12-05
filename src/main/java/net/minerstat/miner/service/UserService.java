package net.minerstat.miner.service;

import net.minerstat.miner.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    List<User> fetchAll();

    User findByEmail(String email);

    void update(User user);

    User add(User user);

    User get(long uid);

    void addAll(Collection<User> users);

    boolean delete(long uid);

}
