package trello.controller.board;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import trello.dao.BoardRepository;
import trello.dao.CardRepository;
import trello.dao.DeckRepository;
import trello.dao.UserRepository;
import trello.model.Board;
import trello.model.Deck;
import trello.model.User;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Secured("ROLE_USER")
	@RequestMapping(value ="", method = RequestMethod.GET)
    public ModelAndView boardsPage(Principal principal) {
	
//	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 왜 되다가 안될까?
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 String username = auth.getName();
	 User user = userRepository.findByUsername(username);
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


//@RequestMapping(value ="/{userId}", method = RequestMethod.GET)
//public ModelAndView projectMain(Model model, @PathVariable Long userId) {
// 
// ModelAndView mav = new ModelAndView("boards");
// mav.addObject("boards", boardRepository.findAll());
// User user = userRepository.findById(userId);
// mav.addObject("user", user);
// return mav;
//}
//