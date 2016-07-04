package trello.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import core.web.argumentresolver.LoginUser;
import trello.dao.BoardRepository;
import trello.dao.DeckRepository;
import trello.model.User;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private BoardRepository boardRepository;

	// @RequestMapping(value = "/{boardId}")
	// public String showBoards(@PathVariable long boardId, ModelAndView mav)
	// throws Exception {
	//
	// if (deckRepository.findOne(boardId) != null) {
	// model.addAttribute("decks", deckRepository.findAll());
	// return "board";
	// }
	// return "board";
	// }

	@RequestMapping(value = "/board/{boardId}")
	public ModelAndView showBoard(@LoginUser User loginUser, @PathVariable long boardId) throws Exception {

		LOGGER.info("{}", deckRepository.findAll());
		
		ModelAndView mav = new ModelAndView("board");	
		mav.addObject("board", boardRepository.findOne(boardId));
		if (deckRepository.findOne(boardId) != null) {			
			mav.addObject("decks", deckRepository.findAll());
			return mav;
		}
		return mav;
	}
}
