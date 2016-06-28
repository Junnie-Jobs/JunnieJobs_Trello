package trello.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.web.argumentresolver.LoginUser;
import trello.dao.BoardRepository;
import trello.model.Board;
import trello.model.User;

@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createForm(@LoginUser User loginUser, String boardName) throws Exception {
		Board newBoard = new Board(boardName);
		return newBoard.getBoardName();
	}
	
	
}
