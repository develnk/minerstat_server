package net.minerstat.miner.controller;

import net.minerstat.miner.entity.UserRig;
import net.minerstat.miner.model.json.request.UserRigRequest;
import net.minerstat.miner.service.impl.UserRigServiceImpl;
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
    private UserRigServiceImpl userRigService;

    // Create new user rig.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorker(@RequestBody UserRigRequest authRequest) throws AuthenticationException {
        UserRig userRig = userRigService.createUserRig(authRequest.getName(), authRequest.getPassword(), authRequest.getRigId());
        return ResponseEntity.ok(userRig);
    }

}
