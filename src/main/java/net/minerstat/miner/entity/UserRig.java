package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "user_rig")
public class UserRig {

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "rig_id", length=64, nullable = false, unique=true)
    private String rigId;

    @JsonIgnore
    @Column(name="uid", nullable = false)
    private Long uid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workers_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    public UserRig() {}

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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
}
