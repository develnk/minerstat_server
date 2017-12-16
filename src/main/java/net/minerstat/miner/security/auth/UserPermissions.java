package net.minerstat.miner.security.auth;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.entity.Worker;
import net.minerstat.miner.model.json.request.WorkerRequest;
import net.minerstat.miner.model.json.request.WorkerStatRequest;
import net.minerstat.miner.model.security.SecurityUser;
import net.minerstat.miner.service.UserService;
import net.minerstat.miner.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("UP")
public final class UserPermissions {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerServiceImpl workerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean uidIsCurrentUser(final String uid) {
        Long principalId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return Long.valueOf(uid).equals(principalId);
    }

    public boolean uidIsCurrentUserWorker(final WorkerStatRequest workerStatRequest) {
        Worker worker = workerService.getWorkerByToken(workerStatRequest.getToken());
        Long principalId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return worker.getUsersRig().getUser().getUid().equals(principalId);
    }

    public boolean checkWorkerCredential(final WorkerStatRequest workerStatRequest)  throws AuthenticationException {
        Worker worker = workerService.getWorkerByToken(workerStatRequest.getToken());
        return !worker.getWorkerId().isEmpty();
    }

    public boolean checkRigWorkerCredential(final WorkerRequest workerRequest) {
        User user = userService.findByEmail(workerRequest.getName());
        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), workerRequest.getPassword())
            );

        return authentication.isAuthenticated();
    }

}
