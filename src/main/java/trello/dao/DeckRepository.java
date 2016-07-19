package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import trello.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, Long> {


//	List<Deck> findAllByBoardId(long boardId);
//	List<Deck> findByBoardId(long boardId);


}
