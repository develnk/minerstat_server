package net.minerstat.miner.model.factory;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.model.security.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

  public static SecurityUser create(User user) {
    return new SecurityUser(
      user.getUid(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getAuthorities()
    );
  }

}
