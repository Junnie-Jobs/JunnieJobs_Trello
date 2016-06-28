package trello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private List members;

	public Board(){}
	
	public Board(String boardName) {
		this.boardName = boardName;
	}

	@OneToMany(mappedBy = "board")
	private java.util.List<List> lists;

	@Override
	public String toString() {
		return "Board [id=" + boardId + ", boardName=" + boardName + "]";
	}
}
