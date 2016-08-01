package trello.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trello.dao.UserRepository;
import trello.model.FacebookUser;
import trello.model.User;


@RestController
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal logout(Principal principal) {
		return principal;
	}
	
	@RequestMapping(value = "/fbUserLogin", method = RequestMethod.POST)
	public Long create(FacebookUser facebookUser, Model model, Principal principal) {
		
		System.out.println("principal에 대해");
		System.out.println(principal);
		User checkUser = userRepository.findByFbId(facebookUser.getFbId());
		if(checkUser == null){		
			User user = new User();
			user.setFbId(facebookUser.getFbId());
			user.setUsername(facebookUser.getUsername());
			userRepository.save(user);
			return user.getId();
		}
		return checkUser.getId();
		
	}

}
