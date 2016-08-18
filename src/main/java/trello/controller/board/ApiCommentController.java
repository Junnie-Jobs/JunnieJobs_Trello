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
//		List<Comment> comments = commentRepository.findAll(cardId);
		return newComment;
	}
	
//	Deck deck = deckRepository.findOne(deckId);
//	Card newCard = new Card(cardName, deck);
	
	
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	public List<Comment> getAllComment(long cardId) throws Exception {
//				
//		List<Comment> comments = commentRepository.findAll(cardId);
//		
//		System.out.println("코멘츠는?");
//		System.out.println(comments);
//		return comments;
//	}
//	

}
