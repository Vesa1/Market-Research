package market.research.model;

public class UserState {

	private String accessToken;
	private Long expiresIn;
	private String role;
	
	public UserState() {
		
	}
	
	public UserState(String accessToken, Long expiresIn, String role) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.role = role;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
