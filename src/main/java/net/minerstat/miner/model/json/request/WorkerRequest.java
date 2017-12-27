package net.minerstat.miner.model.json.request;

import net.minerstat.miner.enums.MinerTypes;

public class WorkerRequest extends AuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515508L;

    private MinerTypes minerType;

    private String rigId;

    public WorkerRequest() {}

    public WorkerRequest(String name, String password, Integer minerType, String rigId) {
        super(name, password);
        setMinerType(minerType);
        setRigId(rigId);
    }

    public MinerTypes getMinerType() {
        return minerType;
    }

    public void setMinerType(Integer minerType) {
        this.minerType = MinerTypes.setValue(minerType);
    }

    public String getRigId() {
        return rigId;
    }

    public void setRigId(String rigId) {
        this.rigId = rigId;
    }
}
