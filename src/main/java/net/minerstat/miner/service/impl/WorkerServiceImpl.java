package net.minerstat.miner.service.impl;

import net.minerstat.miner.dao.WorkerRepository;
import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.entity.WorkerPools;
import net.minerstat.miner.entity.WorkerStat;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.service.WorkerService;
import net.minerstat.miner.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service("WorkerService")
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public Worker newWorker(String email, String password, MinerTypes minerType) {
        User user = userService.findByEmail(email);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        Worker newWorker = new Worker();
        newWorker.setUid(user.getUid());
        newWorker.setWorkerId(UUID.randomUUID().toString());
        newWorker.setToken(tokenUtils.generateTokenWorker());
        newWorker.setMinerType(minerType.value);
        workerRepository.save(newWorker);

//        List<WorkerPools> workerPools = new ArrayList<>();
//        workerPools.add(new WorkerPools("http://ya.ru", newWorker.getWorkerId()));
//        newWorker.setWorkerPools(workerPools);
//        workerRepository.save(newWorker);
//
//        WorkerStat workerStat = new WorkerStat();
//        workerStat.setOneHash(324234);
//        workerStat.setTwoHash(233434);
//        workerStat.setDevAmount(4);
//        workerStat.setUpTime(25);
//        newWorker.setWorkerStat(workerStat);
//        workerRepository.save(newWorker);

        return newWorker;
    }
}
