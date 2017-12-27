package net.minerstat.miner.model.json.request;

public class WorkerStatRequest {

	private static final long serialVersionUID = 6624746180748515607L;

	private String workerId;

	private String date;

	// Json Serialized string.
	private Object data;

	public WorkerStatRequest() {}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
