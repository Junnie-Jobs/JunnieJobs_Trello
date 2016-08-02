package trello.controller.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trello.dao.BoardRepository;
import trello.dao.UserRepository;
import trello.model.Board;
import trello.model.User;


@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Board createBoard(String boardName, Long userId) throws Exception {
		System.out.println("들어온 유저아이디는");
		System.out.println(userId);
		User user = userRepository.findById(userId);
		Board newBoard = new Board(user, boardName);
		boardRepository.save(newBoard);	
		return newBoard;
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Board> delete(@PathVariable Long id) {
//		Board board = boardRepository.findOne(id);
//		if (board == null) {
//			return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
//		}
//		boardRepository.delete(board);
//		return new ResponseEntity<Board>(HttpStatus.NO_CONTENT);
//	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(Long id) {
		System.out.println("들어온 값은");
		System.out.println(id);
		Board board = boardRepository.findOne(id);
		boardRepository.delete(board);
		
	}
	
	

}
