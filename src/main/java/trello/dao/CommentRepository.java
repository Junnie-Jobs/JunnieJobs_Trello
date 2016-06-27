package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Comment;
import trello.model.User;

public interface CommentRepository extends CrudRepository<Comment, Long>{

}
