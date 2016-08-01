package trello.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import trello.dao.BoardRepository;
import trello.dao.UserRepository;


@Controller
@RequestMapping("/boards")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Secured("ROLE_USER")
	@RequestMapping(value ="", method = RequestMethod.GET)
    public ModelAndView boardsPage(@AuthenticationPrincipal User activeUser) {
		System.out.println(activeUser);
	 trello.model.User user = userRepository.findByUsername(activeUser.getUsername());
	 ModelAndView mav = new ModelAndView("boards");
	 mav.addObject("boards", boardRepository.findAll());
	 mav.addObject("user", user);
     return mav;
 }
	
	@RequestMapping(value ="/{userId}", method = RequestMethod.GET)
    public ModelAndView moveToboardsPage(@PathVariable long userId) {
	 trello.model.User user = userRepository.findById(userId);
	 log.info("logined user: {}", user);
	 ModelAndView mav = new ModelAndView("boards");
	 mav.addObject("boards", boardRepository.findAll());
	 mav.addObject("user", user);
     return mav;
 }
	
	@RequestMapping(value = "/board/{boardId}/{userId}")
	public ModelAndView showBoard(@PathVariable long boardId, @PathVariable long userId) throws Exception {
		ModelAndView mav = new ModelAndView("board");	
		System.out.println(boardRepository.findOne(boardId));
		mav.addObject("board", boardRepository.findOne(boardId));
		mav.addObject("user", userRepository.findById(userId));
		return mav;
	}
}

