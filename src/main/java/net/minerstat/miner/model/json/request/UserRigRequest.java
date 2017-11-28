package net.minerstat.miner.model.json.request;

import net.minerstat.miner.enums.MinerTypes;

public class UserRigRequest extends AuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515508L;

    private String rigId;

    public UserRigRequest() {}

    public UserRigRequest(String name, String password, String rigId) {
        super(name, password);
        setRigId(rigId);
    }

    public String getRigId() {
        return rigId;
    }

    private void setRigId(String rigId) {
        this.rigId = rigId;
    }
}
