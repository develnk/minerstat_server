package net.minerstat.miner.service.impl;

import net.minerstat.miner.dao.UserRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.dao.impl.UserDAOImpl;

import java.util.Collection;
import java.util.List;

import net.minerstat.miner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAOImpl userDao;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User get(long uid) {
        return userRepository.findOne(uid);
    }

    @Transactional
    public User add(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    public boolean delete(long uid) {
        return userDao.deleteUser(uid);
    }

    @Transactional
    public void addAll(Collection<User> users) {
        for (User product : users) {
            userDao.insertUser(product);
        }
    }

    @Transactional
    public List<User> fetchAll() {
        return userDao.findAll();
    }

}

