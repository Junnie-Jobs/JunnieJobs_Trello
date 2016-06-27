package trello.model;

import javax.persistence.*;
import javax.servlet.http.HttpSession;


@Entity
public class User {

	public static final GuestUser GUEST_USER = new GuestUser();
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="username")
	private String username;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	
	public User(){}
	
	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isGuestUser() {
		return false;
	}
	
	private static class GuestUser extends User {
		@Override
		public boolean isGuestUser() {
			return true;
		}
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
	public boolean isSameUser(User user) {
		return isSameUser(user.getId());
	}
	
	public boolean isSameUser(Long newUserId) {
		if (id == null) {
			return false;
		}
		return id.equals(newUserId);
	}
	


	
}
