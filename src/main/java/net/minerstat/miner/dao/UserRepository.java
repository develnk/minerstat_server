package net.minerstat.miner.dao;

import net.minerstat.miner.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where username = ?1 OR email = ?1")
    User findUserByUsername(String username);

    Page findAll(Pageable pageable);
}
