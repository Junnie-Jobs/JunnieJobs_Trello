package trello.controller.home;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trello.dao.BoardRepository;
import trello.dao.UserRepository;
import trello.model.User;

@Controller
public class HomeController {
	
private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	 @RequestMapping("")
	    public String moveHome() {		
	        return "index";
	 }
	 
	 @RequestMapping("/signup")
	    public String signUp() {
	        return "signUp";
	 }
	 
	 @RequestMapping("/login")
	    public String login() {
	        return "login";
	 }
	 
}
