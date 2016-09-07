package trello.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import trello.model.Card;
import trello.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findByCard(Card card);  
}
