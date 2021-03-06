package trello.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "deck")
public class Deck {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long deckId;
	
	@Getter
	@Setter
	@Column(name = "deckName", length = 50, nullable = false)
	private String deckName;


	@Getter
	@Setter
	@ManyToOne
	@NotNull
	private Board board;
	
	@Getter(onMethod = @__( @JsonIgnore ))
	@Setter
	@OneToMany(mappedBy = "deck")
	private List<Card> cards;

	public Deck(){}

	public Deck(Board board, String deckName) {
		this.deckName = deckName;
		this.board = board;
	}

	public void addCard(Card card) {
		cards.add(card);
	}
	
	
}