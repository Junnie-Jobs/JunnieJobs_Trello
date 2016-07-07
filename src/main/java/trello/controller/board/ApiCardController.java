package trello.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import trello.dao.BoardRepository;
import trello.dao.CardRepository;
import trello.dao.DeckRepository;
import trello.model.Card;
import trello.model.Deck;

@RestController
@RequestMapping("/api/card")
public class ApiCardController {
	
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private DeckRepository deckRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@RequestMapping(value= "", method = RequestMethod.GET)
	public Iterable<Card> cards() {
		return cardRepository.findAll();
	}
	
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public Card createCard(String cardName, long deckId) throws Exception {
		
		Deck deck = deckRepository.findOne(deckId);
		Card newCard = new Card(cardName, deck);
		cardRepository.save(newCard);
		return newCard;
	}
	

}
