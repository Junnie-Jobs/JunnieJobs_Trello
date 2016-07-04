package trello.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import trello.model.Deck;

public interface DeckRepository extends CrudRepository<Deck, Long> {

//	List<Deck> findAllByBoardId(long boardId);


}
