package trello.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import trello.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{
	
//	List<Card> findByDeckId(long deckId);

}
