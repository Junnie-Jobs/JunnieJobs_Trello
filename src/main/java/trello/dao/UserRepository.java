package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findById(Long Id);
	User findByEmail(String email);
	User findByUsername(String username);
	User findByFbId(String fbId);
}
