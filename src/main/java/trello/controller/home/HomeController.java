package trello.controller.home;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import trello.dao.UserRepository;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

//	@Autowired
//	private UserRepository userRepository;
	
	 @RequestMapping("")
	    public String moveHome() {		
	        return "index";
	 }
	 
	 @RequestMapping("/signUp")
	    public String signUp() {
	        return "signUp";
	 }
	 @RequestMapping("/login")
	    public String login() {
	        return "login";
	 }
	 
	 @RequestMapping("/projectMain")
	    public String projectMain() {
	        return "projectMain";
	 }
	 
	 @RequestMapping("/board")
	    public String board() {
	        return "board";
	 }

}
