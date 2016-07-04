package trello.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import trello.dao.BoardRepository;
import trello.dao.DeckRepository;
import trello.dao.UserRepository;
import trello.model.User;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/board/{boardId}/{userId}")
	public ModelAndView showBoard(@PathVariable long boardId, @PathVariable long userId) throws Exception {

		LOGGER.info("{}", deckRepository.findAll());
		ModelAndView mav = new ModelAndView("board");	
		mav.addObject("board", boardRepository.findOne(boardId));
		mav.addObject("user", userRepository.findById(userId));
		
		if (deckRepository.findOne(boardId) != null) {			
			mav.addObject("decks", deckRepository.findAll());
			return mav;
		}
		return mav;
	}
}
