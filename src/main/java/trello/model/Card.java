package trello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cardId;
	@Column(name = "cardName", length = 70, nullable = false)
	private String cardName;
	@Column(name = "contents", length = 100, nullable = true)
	private String contents;
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_card_parent_id"))
	private Deck deck;

	public Card() {
	}

	public Card(String cardName, Deck deck) {

		this.cardName = cardName;
		this.deck = deck;

	}

}