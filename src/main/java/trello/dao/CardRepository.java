package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import trello.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
	
//	List<Card> findByDeckId(long deckId);

}
