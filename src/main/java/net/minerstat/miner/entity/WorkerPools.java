package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "worker_pools")
public class WorkerPools implements Serializable {

    private static final long serialVersionUID = 2353528370345499817L;

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "pool_url", length=512, nullable = false)
    private String poolUrl;

    @Column(name = "worker_id", length=64, nullable = false)
    private String workerId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "worker_id", referencedColumnName = "worker_id", insertable = false, updatable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    WorkerPools() {}

    public WorkerPools(String url, String workerId) {
        this.setPoolUrl(url);
        this.setWorkerId(workerId);
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getPoolUrl() {
        return poolUrl;
    }

    public void setPoolUrl(String poolUrl) {
        this.poolUrl = poolUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}
