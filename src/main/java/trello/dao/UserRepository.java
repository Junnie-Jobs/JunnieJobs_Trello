package trello.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trello.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findById(Long Id);
	User findByEmail(String email);
}
