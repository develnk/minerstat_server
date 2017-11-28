package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "worker_stat")
public class WorkerStat implements Serializable {

    private static final long serialVersionUID = 2353528370345499818L;

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "up_time")
    private Integer upTime;

    @Column(name = "device_amount")
    private Integer devAmount;

    @Column(name = "one_hash")
    private Integer oneHash;

    @Column(name = "two_hash")
    private Integer twoHash;

    @Column(name = "average_rate")
    private Integer averageRate;

    @OneToOne(mappedBy = "workerStat")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    public WorkerStat() {}

    public Integer getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Integer averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getTwoHash() {
        return twoHash;
    }

    public void setTwoHash(Integer twoHash) {
        this.twoHash = twoHash;
    }

    public Integer getOneHash() {
        return oneHash;
    }

    public void setOneHash(Integer oneHash) {
        this.oneHash = oneHash;
    }

    public Integer getDevAmount() {
        return devAmount;
    }

    public void setDevAmount(Integer devAmount) {
        this.devAmount = devAmount;
    }

    public Integer getUpTime() {
        return upTime;
    }

    public void setUpTime(Integer upTime) {
        this.upTime = upTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
