package net.minerstat.miner.model.json.request;

import net.minerstat.miner.enums.MinerTypes;

public class WorkerRequest extends AuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515508L;

    private MinerTypes minerType;

    public WorkerRequest() {}

    public WorkerRequest(String name, String password, Integer minerType) {
        super(name, password);
        setMinerType(minerType);
    }

    public MinerTypes getMinerType() {
        return minerType;
    }

    private void setMinerType(Integer minerType) {
        this.minerType = MinerTypes.setValue(minerType);
    }
}
