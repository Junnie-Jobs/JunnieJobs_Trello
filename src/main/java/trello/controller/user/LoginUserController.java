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
public class LoginUserController {

	private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String getUserInfo(
								Model model,
								@RequestParam String username,
								@RequestParam String email, 								
								@RequestParam String password, 
								HttpSession session) {
		
		User user = userRepository.findByEmail(email);
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);

		if (user != null) {
			log.debug("유저가 이미 존재하여 바로 프로젝트메인으로 이동합니다");
			model.addAttribute("user", user);
			return "redirect:/boards/"+user.getId();
		}
		
		user = new User(username, email, password);
		userRepository.save(user);

		log.debug("새로운 유저가 추가되었습니다.");
		log.debug("User : {}", user);
		model.addAttribute("user", user);
		return "redirect:/boards/"+user.getId();

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String email, String password, HttpSession session, Model model) throws Exception {
       		
		User user = userRepository.findByEmail(email);

        if (user == null) {
        	log.debug("user가 존재하지 않습니다");
            model.addAttribute("loginFailed", true);
            return "redirect:/";
        }
        
        if (user.matchPassword(password)) {
        	System.out.println("로그인 성공!");
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            model.addAttribute(user);
            return "redirect:/boards/"+user.getId();
   
        } else {
        	log.debug("user의 패스워드가 일치하지 않습니다.");
        	model.addAttribute("loginFailed", true);
            return "redirect:/";
        }
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";	 
	}
}