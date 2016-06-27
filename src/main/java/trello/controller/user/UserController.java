package trello.controller.user;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trello.config.UserSessionUtils;
import trello.dao.UserRepository;
import trello.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody String getUserInfo(
								Model model,
								@RequestParam String username,
								@RequestParam String email, 								
								@RequestParam String password, 
								HttpSession session) {
		
		System.out.println(username);
		User user = userRepository.findByEmail(email);
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);

		if (user != null) {
			log.debug("유저가 이미 존재하여 바로 홈으로 넘어갑니다");
			model.addAttribute("user", user);
			return "redirect:/";
		}

		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);

		userRepository.insert(user);

		log.debug("새로운 유저가 추가되었습니다.");
		log.debug("User : {}", user);
		model.addAttribute("user", user);
		return "redirect:/";

	}

}
