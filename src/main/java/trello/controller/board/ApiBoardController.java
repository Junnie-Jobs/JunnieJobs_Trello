package trello.controller.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trello.dao.BoardRepository;
import trello.model.Board;


@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

	@Autowired
	private BoardRepository boardRepository;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Board createBoard(String boardName) throws Exception {
		Board newBoard = new Board(boardName);
		boardRepository.save(newBoard);	
		return newBoard;
	}
	
//	@RequestMapping(value = "/new", method = RequestMethod.POST)
//	public ResponseEntity<Board> boardNew(@RequestBody Board board) throws Exception {
//		Board saved = boardRepository.save(board);
//		return new ResponseEntity<Board>(saved, HttpStatus.CREATED);
//	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Board> delete(@PathVariable Long id) {
		Board board = boardRepository.findOne(id);
		if (board == null) {
			return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
		}
		boardRepository.delete(board);
		return new ResponseEntity<Board>(HttpStatus.NO_CONTENT);
	}
	
	

}
