package net.minerstat.miner.service;

import net.minerstat.miner.dao.*;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UsersRig;
import net.minerstat.miner.model.json.request.UserRigRequest;
import net.minerstat.miner.model.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service("UsersRigService")
public class UsersRigService {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRigDao usersRigDAO;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersRigRepository usersRigRepository;

    public UsersRig createUserRig(UserRigRequest userRigRequest) {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User realUser = userRepository.findOne(user.getId());

        UsersRig userRig = new UsersRig();
        userRig.setRigId(UUID.randomUUID().toString());
        userRig.setName(userRigRequest.getRigName());
        userRig.setUser(realUser);
        usersRigDAO.insertUsersRig(userRig);
        return userRig;
    }

    public List<UsersRig> getAllUserRigs() {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User realUser = userRepository.findOne(user.getId());
        return usersRigRepository.findAllRigsUser(realUser.getUid());
    }

}
