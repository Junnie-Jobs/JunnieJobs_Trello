package trello.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trello.dao.CardRepository;
import trello.dao.CommentRepository;
import trello.model.Card;
import trello.model.Comment;
import trello.model.Deck;


@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {
		
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CommentRepository commentRepository;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Comment createComment(long cardId, String username, String contents, String timeStamp) throws Exception {

		Card card = cardRepository.findOne(cardId);
		Comment newComment = new Comment(card, username, contents, timeStamp);
		commentRepository.save(newComment);

		return newComment;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public List<Comment> showComment(long cardId) throws Exception {

		Card card = cardRepository.findOne(cardId);
		List<Comment> comments = commentRepository.findByCard(card);

		return comments;
	}

}
