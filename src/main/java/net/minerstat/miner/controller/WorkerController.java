package net.minerstat.miner.controller;

import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.request.WorkerStatRequest;
import net.minerstat.miner.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/v1/worker"})
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    // Create new worker.
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorker(@RequestBody WorkerRequest workerRequest) throws AuthenticationException {
        Worker worker = workerService.newWorker(workerRequest);
        // Return the worker with token.
        return ResponseEntity.ok(worker);
    }

    // Save statistic to DB.
    @RequestMapping(value = "stat", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> statisticToDB(@RequestBody WorkerStatRequest workerStatRequest) throws AuthenticationException {
        Boolean result = workerService.saveStat(workerStatRequest);
        return ResponseEntity.ok(result);
    }

}
