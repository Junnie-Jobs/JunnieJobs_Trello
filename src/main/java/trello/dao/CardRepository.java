package trello.dao;

import org.springframework.data.repository.CrudRepository;

import trello.model.Card;

public interface CardRepository extends CrudRepository<Card, Long>{

}
