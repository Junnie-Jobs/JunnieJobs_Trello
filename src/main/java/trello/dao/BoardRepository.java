package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import trello.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{


}
