package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker implements Serializable {
    private static final long serialVersionUID = 2353528370345499815L;

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "worker_id", length=64, nullable = false, unique=true)
    private String workerId;

    @Column(name = "token", length=64, nullable = false)
    private String token;

    @JsonIgnore
    @Column(name="miner_type", nullable = false)
    private Integer minerType;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<WorkerPools> workerPools = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_stat_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private WorkerStat workerStat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_stat_detail_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private WorkerStatDetail workerStatDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_stat_detail_gpu_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private WorkerStatDetailGPU workerStatDetailGPU;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_rig_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private UsersRig usersRig;

    public Worker() {}

    Worker(Long id) {
        this.setId(id);
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getMinerType() {
        return minerType;
    }

    public void setMinerType(Integer minerType) {
        this.minerType = minerType;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public List<WorkerPools> getWorkerPools() {
        return workerPools;
    }

    public void setWorkerPools(List<WorkerPools> workerPools) {
        this.workerPools = workerPools;
    }

    public WorkerStat getWorkerStat() {
        return workerStat;
    }

    public void setWorkerStat(WorkerStat workerStat) {
        this.workerStat = workerStat;
    }

    public WorkerStatDetail getWorkerStatDetail() {
        return workerStatDetail;
    }

    public void setWorkerStatDetail(WorkerStatDetail workerStatDetail) {
        this.workerStatDetail = workerStatDetail;
    }

    public UsersRig getUsersRig() {
        return usersRig;
    }

    public void setUsersRig(UsersRig usersRig) {
        this.usersRig = usersRig;
    }

    public WorkerStatDetailGPU getWorkerStatDetailGPU() {
        return workerStatDetailGPU;
    }

    public void setWorkerStatDetailGPU(WorkerStatDetailGPU workerStatDetailGPU) {
        this.workerStatDetailGPU = workerStatDetailGPU;
    }
}
