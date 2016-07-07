package trello.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.codehaus.jackson.annotate.JsonIgnore;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@JsonIgnoreProperties({"deck"})
@Data
@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cardId;
	@Column(name = "title", length = 150, nullable = false)
	private String title;
	@Column(name = "description", length = 3000, nullable = true)
	private String description;
	
//	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_deck_id"))
	private Deck deck;

	public Card() {
	}

	public Card(String title, Deck deck) {

		this.title = title;
		this.deck = deck;
		deck.addCard(this);

	}

}