package trello.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import trello.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	
}
