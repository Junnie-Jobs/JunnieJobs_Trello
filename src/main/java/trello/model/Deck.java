package trello.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;
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