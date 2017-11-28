package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "worker_stat_detail_gpu")
public class WorkerStatDetailGPU {

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "gpu_index")
    private Integer gpuIndex;

    @Column(name = "one_hash")
    private Integer oneHash;

    @Column(name = "two_hash")
    private Integer twoHash;

    @Column(name = "temp")
    private Integer temp;

    @Column(name = "fan_speed")
    private Integer fanSpeed;

    @OneToOne(mappedBy = "workerStatDetailGPU")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_stat_detail_gpu_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private WorkerStatGPUInfo workerStatGPUInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGpuIndex() {
        return gpuIndex;
    }

    public void setGpuIndex(Integer gpuIndex) {
        this.gpuIndex = gpuIndex;
    }

    public Integer getOneHash() {
        return oneHash;
    }

    public void setOneHash(Integer oneHash) {
        this.oneHash = oneHash;
    }

    public Integer getTwoHash() {
        return twoHash;
    }

    public void setTwoHash(Integer twoHash) {
        this.twoHash = twoHash;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(Integer fanSpeed) {
        this.fanSpeed = fanSpeed;
    }
}
