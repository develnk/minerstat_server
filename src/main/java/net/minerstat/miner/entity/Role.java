package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="role")
public class Role implements Serializable {

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name="name")
    private String name;

    Role() {}

    Role(Long id) {
        this.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
