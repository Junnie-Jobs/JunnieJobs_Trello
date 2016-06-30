package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long> {

//	Object findAll(long boardId);

}
