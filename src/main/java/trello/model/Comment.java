package trello.model;

import java.sql.Timestamp;

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
@Data
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private long time;
	@Column(name = "content", length=1000, nullable=false)
	private String content;
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_comment_parent_id"))
	private Card card;
	

	public Comment(String username, long DeckId, long cardId, String content, long time) {
		
		this.username = username;
		this.content = content;
		this.time = time;
	}



	
}