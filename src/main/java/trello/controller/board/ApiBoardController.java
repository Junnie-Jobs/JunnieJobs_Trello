package trello.controller.board;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import trello.dao.BoardRepository;
import trello.model.Board;
import trello.model.Card;
import trello.model.Comment;
import trello.model.User;


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
	
	@RequestMapping(value = "/boardNew", method = RequestMethod.POST)
	public ResponseEntity<Board> boardNew(@RequestBody Board board) throws Exception {
		Board saved = boardRepository.save(board);
		return new ResponseEntity<Board>(saved, HttpStatus.CREATED);
	}
	
	

//	@RequestMapping(value = "/newList", method = RequestMethod.POST)
//	public String createList(@LoginUser User loginUser, String listName) throws Exception {
//		List newList = new List(listName);
//		return newList.getListName();
//	}
//
//	@RequestMapping(value = "/{listId}/newCard", method = RequestMethod.POST)
//	public String createCard(@LoginUser User loginUser, @PathVariable long listId, String cardName) throws Exception {
//		Card newCard = new Card(cardName);
//		return newCard.getCardName();
//	}
//
//	@RequestMapping(value = "/{listId}/{cardId}/newComment", method = RequestMethod.POST)
//	public Comment addComment(@LoginUser User loginUser, @PathVariable long listId, @PathVariable long cardId, String contents)
//			throws Exception {
//
//		long currentTime = System.currentTimeMillis();
//		Comment comment = new Comment(loginUser.getUsername(), listId, cardId, contents, currentTime);
////		Comment comment = new Comment(loginUser.getUsername(), contents, currentTime);
//		Comment savedComment = boardRepository.save(comment);
//		boardRepository.updatecountOfComment(savedComment.getId());
//		return comment;
//	}

}
