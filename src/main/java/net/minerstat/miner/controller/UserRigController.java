package net.minerstat.miner.controller;

import net.minerstat.miner.entity.UsersRig;
import net.minerstat.miner.model.json.request.AuthenticationRequest;
import net.minerstat.miner.model.json.request.UserRigRequest;
import net.minerstat.miner.service.UsersRigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/rig"})
public class UserRigController {

    @Autowired
    private UsersRigService usersRigService;

    // Create new user rig.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PreAuthorize("hasAuthority('Authenticated') AND isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUserRig(@RequestBody UserRigRequest authRequest) throws AuthenticationException {
        UsersRig userRig = usersRigService.createUserRig(authRequest);
        return ResponseEntity.ok(userRig);
    }

    // Get all user rigs.
    @RequestMapping(value = "all_rigs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('Authenticated') AND isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserRigs() throws AuthenticationException  {
        List<UsersRig> usersRigList = usersRigService.getAllUserRigs();
        return ResponseEntity.ok(usersRigList);
    }

}
