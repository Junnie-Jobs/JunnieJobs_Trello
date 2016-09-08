package trello.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trello.model.Board;
import trello.model.User;

public interface BoardRepository extends JpaRepository<Board, Long>{

	List<Board> findByCreator(User user);

}
