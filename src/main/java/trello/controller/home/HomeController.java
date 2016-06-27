package trello.controller.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
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
