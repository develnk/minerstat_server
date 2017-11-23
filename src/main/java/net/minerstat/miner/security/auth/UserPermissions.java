package net.minerstat.miner.security.auth;

import net.minerstat.miner.model.security.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("UP")
public final class UserPermissions {

    public boolean uidIsCurrentUser(final String uid) {
        Long principalId = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return Long.valueOf(uid).equals(principalId);
    }

}
