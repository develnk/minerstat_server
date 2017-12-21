package net.minerstat.miner.model.json.request;

import org.json.simple.JSONObject;

public class WorkerStatRequest {

	private static final long serialVersionUID = 6624726180748515607L;

	private String workerId;

	// Json Serialized string.
	private JSONObject logs;

	public WorkerStatRequest() {}


	public JSONObject getLogs() {
		return logs;
	}

	public void setLogs(JSONObject logs) {
		this.logs = logs;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
}
