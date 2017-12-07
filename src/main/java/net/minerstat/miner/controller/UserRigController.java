package net.minerstat.miner.controller;

import net.minerstat.miner.entity.UsersRig;
import net.minerstat.miner.model.json.request.AuthenticationRequest;
import net.minerstat.miner.model.json.request.UsersRigRequest;
import net.minerstat.miner.service.UsersRigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/v1/rig"})
public class UserRigController {

    @Autowired
    private UsersRigService usersRigService;

    // Create new user rig.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorker(@RequestBody AuthenticationRequest authRequest) throws AuthenticationException {
        UsersRig userRig = usersRigService.createUserRig(authRequest.getName(), authRequest.getPassword());
        return ResponseEntity.ok(userRig);
    }

}
