package trello.controller.home;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(Model model, HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView("index");
//		model.addAttribute("user", new User());
		log.debug("Home 화면으로 이동");
		return mav;
	}
	
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


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
