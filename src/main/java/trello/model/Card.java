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

@Entity
@Table(name = "card")
public class Card {
	private static Card card = null;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "cardName", length=70, nullable=false)
	private String cardName;
	@Column(name="contents", length=100, nullable=true)
	private String contents;
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_card_parent_id"))
	private List list;
	
	public Card(){}
	
	public Card(String cardName){
		this.cardName = cardName;
	}
	
	public Card(String cardName, String contents){
		this.cardName = cardName;
		this.contents = contents;
	}
	
	public static Card getCard(){
		return card;
	}

	public long getId() {
		return id;
	}

	public String getCardName() {
		return cardName;
	}

	public String getcontents() {
		return contents;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", cardName=" + cardName + ", contents=" + contents + "]";
	}
	
}