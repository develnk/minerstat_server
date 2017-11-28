package net.minerstat.miner.controller;

import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.model.json.request.AuthenticationRequest;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.response.AuthenticationResponse;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/v1/worker"})
public class WorkerController {

    @Autowired
    WorkerServiceImpl workerService;

    // Create new worker.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorker(@RequestBody WorkerRequest authRequest) throws AuthenticationException {
        Worker worker = workerService.newWorker(authRequest.getName(), authRequest.getPassword(), authRequest.getMinerType());
        // Return the token
        return ResponseEntity.ok(worker);
    }

}
