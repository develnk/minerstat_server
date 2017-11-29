package net.minerstat.miner.service.impl;

import net.minerstat.miner.dao.WorkerRepository;
import net.minerstat.miner.dao.impl.RoleDAOImpl;
import net.minerstat.miner.dao.impl.UsersRigDAOImpl;
import net.minerstat.miner.dao.impl.WorkerDAOImpl;
import net.minerstat.miner.entity.*;
import net.minerstat.miner.enums.MinerTypes;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.request.WorkerStatRequest;
import net.minerstat.miner.security.TokenUtils;
import net.minerstat.miner.service.WorkerService;
import net.minerstat.miner.service.statitistic.AbstractFactory;
import net.minerstat.miner.service.statitistic.Algorithm;
import net.minerstat.miner.service.statitistic.MinerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

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

    @Autowired
    private RoleDAOImpl roleDAO;

    @Autowired
    private UsersRigDAOImpl usersRigDAO;

    @Autowired
    private WorkerDAOImpl workerDAO;

    @Override
    public Worker newWorker(WorkerRequest workerRequest) {
        checkUserCredential(workerRequest.getName(), workerRequest.getPassword());
        UsersRig userRig = usersRigDAO.findByRigId(workerRequest.getRigId());

        Worker newWorker = new Worker();
        newWorker.setUsersRig(userRig);
        newWorker.setWorkerId(UUID.randomUUID().toString());
        newWorker.setToken(tokenUtils.generateTokenWorker());
        newWorker.setMinerType(workerRequest.getMinerType().value);
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

    @Override
    public Boolean saveStat(WorkerStatRequest workerStatRequest) {
        Worker worker = getWorkerByToken(workerStatRequest.getToken());
        MinerTypes minerType = MinerTypes.setValue(worker.getMinerType());
        AbstractFactory factoryMiner = new MinerFactory();
        Algorithm algorithm = factoryMiner.getMiner(minerType);
        algorithm.parseAlgorithm(workerStatRequest.getLogs());
        return true;
    }

    private void checkUserCredential(String userName, String passwd)  throws AuthenticationException {
        User user = userService.findByEmail(userName);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), passwd)
        );
    }

    private User getUserByWorkerToken(String token) throws AuthenticationException {
        User user = workerDAO.getUserByWorkerToken(token);
        if (user == null) {
            throw new BadCredentialsException("Invalid Worker Token");
        }

        return user;
    }

    private Worker getWorkerByToken(String token) {
        Worker worker = workerDAO.getWorkerByToken(token);
        if (worker == null) {
            throw new BadCredentialsException("Invalid Worker Token");
        }

        return worker;
    }


}
