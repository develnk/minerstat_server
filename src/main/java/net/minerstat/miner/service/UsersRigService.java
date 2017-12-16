package net.minerstat.miner.service;

import net.minerstat.miner.dao.UsersRigDao;
import net.minerstat.miner.dao.UsersRigRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UsersRig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("UsersRigService")
public class UsersRigService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRigDao usersRigDAO;

    @Autowired
    private UsersRigRepository usersRigRepository;


    public UsersRig createUserRig(String email, String password) {
        User user = userService.findByEmail(email);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        UsersRig userRig = new UsersRig();
        userRig.setRigId(UUID.randomUUID().toString());
        userRig.setUser(user);
        usersRigDAO.insertUsersRig(userRig);
        return userRig;
    }

    public List<UsersRig> getAllUserRigs(String email, String password) {
        User user = userService.findByEmail(email);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        return usersRigRepository.findAllRigsUser(user.getUid());
    }

}
