package trello.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import trello.dao.BoardRepository;
import trello.dao.DeckRepository;
import trello.model.Board;
import trello.model.Deck;

@RestController
@RequestMapping("/api/deck")
public class ApiDeckController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Deck createDeck(String deckName, long boardId) throws Exception {
		
		Board board = boardRepository.findOne(boardId);
		Deck newDeck = new Deck(board, deckName);
//		board.getDecks().add(newDeck);
		deckRepository.save(newDeck);
//		return new ResponseEntity<Deck>(newDeck, HttpStatus.CREATED);
		return newDeck;
	}
	
	
}
