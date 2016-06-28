package trello.controller.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import core.web.argumentresolver.LoginUser;
import trello.dao.BoardRepository;
import trello.model.Board;
import trello.model.Card;
import trello.model.Comment;
import trello.model.List;
import trello.model.User;

@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

	@Autowired
	private BoardRepository boardRepository;

	@RequestMapping(value = "/newBoard", method = RequestMethod.POST)
	public String createBoard(@LoginUser User loginUser, String boardName) throws Exception {
		Board newBoard = new Board(boardName);
		return newBoard.getBoardName();
	}

	@RequestMapping(value = "/newList", method = RequestMethod.POST)
	public String createList(@LoginUser User loginUser, String listName) throws Exception {
		List newList = new List(listName);
		return newList.getListName();
	}

	@RequestMapping(value = "/{listId}/newCard", method = RequestMethod.POST)
	public String createCard(@LoginUser User loginUser, @PathVariable long listId, String cardName) throws Exception {
		Card newCard = new Card(cardName);
		return newCard.getCardName();
	}

	@RequestMapping(value = "/{listId}/{cardId}/newComment", method = RequestMethod.POST)
	public Comment addComment(@LoginUser User loginUser, @PathVariable long listId, @PathVariable long cardId, String contents)
			throws Exception {

		long currentTime = System.currentTimeMillis();
		Comment comment = new Comment(loginUser.getUsername(), listId, cardId, contents, currentTime);
//		Comment comment = new Comment(loginUser.getUsername(), contents, currentTime);
		Comment savedComment = boardRepository.save(comment);
		boardRepository.updatecountOfComment(savedComment.getId());
		return comment;
	}

}
