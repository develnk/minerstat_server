package net.minerstat.miner.service.impl;

import net.minerstat.miner.entity.User;
import net.minerstat.miner.model.factory.UserFactory;
import net.minerstat.miner.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = this.userRepository.findUserByUsername(name);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("No user found with name '%s'.", name));
    } else {
      return UserFactory.create(user);
    }
  }

}
