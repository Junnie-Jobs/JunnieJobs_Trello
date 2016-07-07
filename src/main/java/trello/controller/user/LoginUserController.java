package trello.controller.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import trello.dao.UserRepository;
import trello.model.User;
import trello.service.CustomUserDetailsService;


@Controller
@RequestMapping("/users")
public class LoginUserController {
	
	//로그인한 사용자정보 가져오는 방법
	//@AuthenticationPrincipal User user

	private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
//	
//	private CustomUserDetailsService cud;
	

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
		public String signUpForm() {
			return "signUp";
		}
		
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(User user) {
		
		
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null) {
		log.debug("유저가 이미 존재하여 로그인 페이지로 이동합니다");
		return "redirect:/users/login";
	}

		log.debug("새로운 유저가 추가되었습니다.");
		log.debug("user : {}", user);
		user.encodePassword(passwordEncoder);
		userRepository.save(user);
		return "redirect:/users/login";

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user) {
		System.out.println(user);
		
		User newUser = userRepository.findByEmail(user.getEmail());

		log.info("logined User {}",newUser);

//		User user = userRepository.findByEmail(email);
//		
//		if (user == null) {
//			log.info("user가 존재하지 않습니다");
//			System.out.println("유저가 존재 하지 않나?");
//			model.addAttribute("loginFailed", true);
//			return "redirect:/users/login";
//		}
//		
//		String encodedPassword = passwordEncoder.encode(password);
//		
//		if(passwordEncoder.matches(password, encodedPassword)){
//			
//			session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
//			return "redirect:/boards/" + user.getId();
//		}else {
//			log.info("user의 패스워드가 일치하지 않습니다.");
//			model.addAttribute("loginFailed", true);
//			return "redirect:/";
//		}
//		}
		return "redirect:/boards/"+user.getId();
	}
	
	



	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
}
