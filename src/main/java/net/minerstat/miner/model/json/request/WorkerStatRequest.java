package net.minerstat.miner.model.json.request;

public class WorkerStatRequest {

	private static final long serialVersionUID = 6624726180748515607L;

	private String token;

	// Json Serialized string.
	private String logs;

	public WorkerStatRequest() {}


	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
