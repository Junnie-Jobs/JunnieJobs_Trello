package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

}
