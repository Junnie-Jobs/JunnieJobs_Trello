package trello.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import trello.dao.BoardRepository;
import trello.dao.DeckRepository;


@Controller
@RequestMapping("/boards")
public class BoardController {

//
	@Autowired
	private DeckRepository deckRepository;

	@RequestMapping(value = "/board/{boardId}")
	public String showBoard(@PathVariable long boardId, Model model) throws Exception {

		if (deckRepository.findOne(boardId) != null) {
			model.addAttribute("decks", deckRepository.findAll());
			return "board";
		}
		return "board";
	}

}
