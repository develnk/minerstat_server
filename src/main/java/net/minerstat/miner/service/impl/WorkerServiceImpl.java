package net.minerstat.miner.service.impl;

import net.minerstat.miner.dao.RoleDao;
import net.minerstat.miner.dao.UserRepository;
import net.minerstat.miner.dao.UsersRigDao;
import net.minerstat.miner.dao.WorkerDao;
import net.minerstat.miner.entity.*;
import net.minerstat.miner.enums.MinerTypes;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.request.WorkerStatRequest;
import net.minerstat.miner.model.security.SecurityUser;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.service.UserService;
import net.minerstat.miner.service.WorkerService;
import net.minerstat.miner.service.statistic.Factory;
import net.minerstat.miner.service.statistic.Algorithm;
import net.minerstat.miner.service.statistic.MinerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("WorkerService")
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RoleDao roleDAO;

    @Autowired
    private UsersRigDao usersRigDAO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerDao workerDAO;

    @Autowired
    private Factory factoryMiner;

    @Override
    public Worker newWorker(WorkerRequest workerRequest) {
        UsersRig userRig = usersRigDAO.findByRigId(workerRequest.getRigId());

        Worker newWorker = new Worker();
        newWorker.setUsersRig(userRig);
        newWorker.setWorkerId(UUID.randomUUID().toString());
        newWorker.setMinerType(workerRequest.getMinerType().value);
        workerDAO.saveWorker(newWorker);

//        List<WorkerPools> workerPools = new ArrayList<>();
//        workerPools.add(new WorkerPools("http://ya.ru", newWorker.getWorkerId()));
//        newWorker.setWorkerPools(workerPools);
//        workerDAO.saveWorker(newWorker);

//        WorkerStat workerStat = new WorkerStat();
//        workerStat.setOneHash(324234);
//        workerStat.setTwoHash(233434);
//        workerStat.setDevAmount(4);
//        workerStat.setUpTime(25);
//        newWorker.setWorkerStat(workerStat);
//        workerDAO.saveWorker(newWorker);

        return newWorker;
    }

    @Override
    public Boolean saveStat(WorkerStatRequest workerStatRequest) {
        Worker worker = workerDAO.findByWorkerId(workerStatRequest.getWorkerId());
        MinerTypes minerType = MinerTypes.setValue(worker.getMinerType());
        Algorithm algorithm = factoryMiner.getMiner(minerType);
        algorithm.parseAlgorithm(worker, workerStatRequest.getData());
        return true;
    }

    @Override
    public List<Worker> findUserWorkers() {
        SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User realUser = userRepository.findOne(user.getId());
        return workerDAO.userWorkers(realUser.getUid());
    }


}
