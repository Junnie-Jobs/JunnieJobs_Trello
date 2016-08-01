package trello.model;

import lombok.Data;

@Data
public class FacebookUser {
	

	private String username;
	private String fbId;
	private String accessToken;

	public FacebookUser() {
	}
	
	public FacebookUser(String fbId, String username){
		this.fbId = fbId;
		this.username = username;
	}
	
	public FacebookUser(String fbId, String username, String accessToken){
		this.fbId = fbId;
		this.username = username;
		this.accessToken = accessToken;
	}

}
