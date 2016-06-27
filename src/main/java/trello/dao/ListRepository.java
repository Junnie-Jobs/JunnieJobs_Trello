package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.List;

public interface ListRepository extends CrudRepository<List, Long>{

}
