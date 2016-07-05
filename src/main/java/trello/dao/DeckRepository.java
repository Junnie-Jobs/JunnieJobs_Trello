package trello.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import trello.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, Long> {


//	List<Deck> findAllByBoardId(long boardId);
//	List<Deck> findByBoardId(long boardId);


}
