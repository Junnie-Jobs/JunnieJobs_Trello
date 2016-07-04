package trello.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "deck")
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long deckId;
	@Column(name = "deckName", length = 50, nullable = false)
	private String deckName;
	@Column(name="description")
	private String description;	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_deck_parent_id"))
	private Board board;
	
	public Deck(){}

	public Deck(String deckName, Board board) {
		this.deckName = deckName;
		this.board = board;
	}

}