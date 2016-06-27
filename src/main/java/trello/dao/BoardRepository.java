package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Board;


public interface BoardRepository extends CrudRepository<Board, Long>{

}
