package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "users_rig")
public class UsersRig {

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "rig_id", length=64, nullable = false, unique=true)
    private String rigId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workers_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    public UsersRig() {}

    public String getRigId() {
        return rigId;
    }

    public void setRigId(String rigId) {
        this.rigId = rigId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
