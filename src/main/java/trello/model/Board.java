package trello.model;



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
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long boardId;
	@Column(name = "boardName", length = 50, nullable = false)
	private String boardName;
		
	@Column(name = "boardHasList")
	@OneToMany
	private java.util.List<Deck> decks;
//	
//	@Column(name = "members")
//	@OneToMany
//	private List members;

	public Board(){}
	
	public Board(String boardName) {
		this.boardName = boardName;
	}


}
