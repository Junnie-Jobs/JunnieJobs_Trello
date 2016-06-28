package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Board;
import trello.model.Comment;


public interface BoardRepository extends CrudRepository<Board, Long>{

	Comment save(Comment comment);
	void updatecountOfComment(long id);

}
