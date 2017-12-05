package net.minerstat.miner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "worker_stat_detail")
public class WorkerStatDetail {

    @Id
    @Column(name="id", nullable=false, insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "one_hash")
    private Integer oneHash;

    @Column(name = "one_number")
    private Integer oneNumber;

    @Column(name = "one_rejected")
    private Integer oneRejected;

    @Column(name = "one_invalid")
    private Integer oneInvalid;

    @Column(name = "two_hash")
    private Integer twoHash;

    @Column(name = "two_number")
    private Integer twoNumber;

    @Column(name = "two_rejected")
    private Integer twoRejected;

    @Column(name = "two_invalid")
    private Integer twoInvalid;

    @OneToOne(mappedBy = "workerStatDetail")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Worker worker;

    public WorkerStatDetail() {}

    public Integer getOneHash() {
        return oneHash;
    }

    public void setOneHash(Integer oneHash) {
        this.oneHash = oneHash;
    }

    public Integer getOneNumber() {
        return oneNumber;
    }

    public void setOneNumber(Integer oneNumber) {
        this.oneNumber = oneNumber;
    }

    public Integer getOneRejected() {
        return oneRejected;
    }

    public void setOneRejected(Integer oneRejected) {
        this.oneRejected = oneRejected;
    }

    public Integer getOneInvalid() {
        return oneInvalid;
    }

    public void setOneInvalid(Integer oneInvalid) {
        this.oneInvalid = oneInvalid;
    }

    public Integer getTwoHash() {
        return twoHash;
    }

    public void setTwoHash(Integer twoHash) {
        this.twoHash = twoHash;
    }

    public Integer getTwoNumber() {
        return twoNumber;
    }

    public void setTwoNumber(Integer twoNumber) {
        this.twoNumber = twoNumber;
    }

    public Integer getTwoRejected() {
        return twoRejected;
    }

    public void setTwoRejected(Integer twoRejected) {
        this.twoRejected = twoRejected;
    }

    public Integer getTwoInvalid() {
        return twoInvalid;
    }

    public void setTwoInvalid(Integer twoInvalid) {
        this.twoInvalid = twoInvalid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
