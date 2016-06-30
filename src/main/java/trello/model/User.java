package trello.model;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	
	public static final GuestUser GUEST_USER = new GuestUser();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
//	@ManyToMany
//	private List boards;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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
	
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}

		return this.password.equals(password);
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
