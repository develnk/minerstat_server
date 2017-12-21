package net.minerstat.miner.model.json.request;

public class UserRigRequest {

    private static final long serialVersionUID = 6624726180748515508L;

    private String rigId;

    private String rigName;

    public UserRigRequest() {}

    public UserRigRequest(String rigId) {
        setRigId(rigId);
    }

    public String getRigId() {
        return rigId;
    }

    private void setRigId(String rigId) {
        this.rigId = rigId;
    }

    public String getRigName() {
        return rigName;
    }

    public void setRigName(String rigName) {
        this.rigName = rigName;
    }

}
