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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board")
public class Board {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long boardId;
	
	@Getter
	@Setter
	@Column(name = "boardName", length = 50, nullable = false)
	private String boardName;
	
	@ManyToOne
	@JoinColumn(
			foreignKey = @ForeignKey(name = "fk_creator_id"), 
			nullable = false)
	private User creator;
	
	
	@JsonIgnore	
	@OneToMany(mappedBy = "board")
	@Getter(onMethod = @__( @JsonIgnore ))
	@Setter
	private List<Deck> decks;

	public Board(){}
	
	public Board(User user, String boardName) {
		this.creator = user;
		this.boardName = boardName;
	}
	
	public Board( String boardName) {
		this.boardName = boardName;
	}
	
	public void createdBy(User user) {
		this.creator = user;
	}


}
