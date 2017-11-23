package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users_roles")
public class UsersRoles implements GrantedAuthority {
    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name="uid", nullable = false)
    private Long uid;

    @JsonIgnore
    @Column(name="rid", nullable = false)
    private Long rid;

    @OneToOne
    @JoinColumn(name = "rid", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    UsersRoles() {}

    public UsersRoles(Long uid, Long rid) {
        this.setUid(uid);
        this.setRid(rid);
        this.role = new Role(rid);
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String getAuthority() {
        return role.getName();
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
