package net.minerstat.miner.security.auth;

import net.minerstat.miner.dao.WorkerDao;
import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.model.json.request.WorkerStatRequest;
import net.minerstat.miner.model.security.SecurityUser;
import net.minerstat.miner.service.UserService;
import net.minerstat.miner.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("UP")
public final class UserPermissions {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean uidIsCurrentUser(final String uid) {
        Long principalId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return Long.valueOf(uid).equals(principalId);
    }

    public boolean uidIsCurrentUserWorker(final WorkerStatRequest workerStatRequest) {
        Worker worker = workerDao.findByWorkerId(workerStatRequest.getWorkerId());
        Long principalId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return worker.getUsersRig().getUser().getUid().equals(principalId);
    }

}
