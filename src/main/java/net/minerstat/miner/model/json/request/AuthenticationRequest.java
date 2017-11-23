package net.minerstat.miner.model.json.request;

public class AuthenticationRequest {

	private static final long serialVersionUID = 6624726180748515507L;
	private String name;
	private String password;

	public AuthenticationRequest() {}

	public AuthenticationRequest(String name, String password) {
		this.setName(name);
		this.setPassword(password);
	}

	public String getName() {
		return this.name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

}
