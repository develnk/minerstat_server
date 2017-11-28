package net.minerstat.miner.service.impl;

import net.minerstat.miner.dao.impl.UserRigDAOImpl;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.UserRig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service("UserRigService")
public class UserRigServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRigDAOImpl userRigDAO;


    public UserRig createUserRig(String email, String password, String rigId) {
        User user = userService.findByEmail(email);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        UserRig userRig = new UserRig();
        userRig.setRigId(rigId);
        userRig.setUid(user.getUid());
        userRigDAO.insertUserRig(userRig);
        return userRig;
    }

}
