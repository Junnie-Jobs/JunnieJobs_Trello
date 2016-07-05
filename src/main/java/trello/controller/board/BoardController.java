package trello.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trello.dao.BoardRepository;
import trello.dao.CardRepository;
import trello.dao.DeckRepository;
import trello.dao.UserRepository;
import trello.model.Board;
import trello.model.User;


@Controller
@RequestMapping("/boards")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	 @RequestMapping(value ="/boards/{userId}", method = RequestMethod.GET)
	    public ModelAndView projectMain(Model model, @PathVariable Long userId) {
		 
		 ModelAndView mav = new ModelAndView("boards");
		 mav.addObject("boards", boardRepository.findAll());
		 User user = userRepository.findById(userId);
		 mav.addObject("user", user);
	     return mav;
	 }
	 
	
	@RequestMapping(value = "/board/{boardId}/{userId}")
	public ModelAndView showBoard(@PathVariable long boardId, @PathVariable long userId) throws Exception {

		log.info("{}", deckRepository.findAll());

		Board board = boardRepository.findOne(boardId);		
		ModelAndView mav = new ModelAndView("board");	
		mav.addObject("board", boardRepository.findOne(boardId));
		mav.addObject("user", userRepository.findById(userId));
		mav.addObject("decks", board.getDecks());
		mav.addObject("cards", cardRepository.findAll());

		return mav;
	}
}
