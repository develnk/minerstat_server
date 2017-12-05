package net.minerstat.miner.dao.impl;

import net.minerstat.miner.dao.RoleDao;
import net.minerstat.miner.dao.RoleRepository;
import net.minerstat.miner.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly=true)
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Transactional(readOnly=true)
    public Long findRidByName(String name) {
        return roleRepository.findRidByName(name);
    }
}
