package trello.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.web.argumentresolver.LoginUser;
import trello.dao.BoardRepository;
import trello.model.Board;
import trello.model.User;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@RequestMapping(value = "/{boardId}", method = RequestMethod.POST)
	public String showBoard(@PathVariable long boardId, Model model) throws Exception {
		model.addAttribute("lists", boardRepository.findAll());
		return "list";
	}
	
	
	
	

}
