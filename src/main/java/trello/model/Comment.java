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
	private long commentId;
	@Column(name = "username", nullable=false)
	private String username;
	@Column(name = "timeStamp")
	private String timeStamp;
	@Column(name = "contents", length=1000, nullable=false)
	private String contents;
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_comment_parent_id"))
	private Card card;
	

	public Comment(){}


	public Comment(Card card, String username, String contents, String timeStamp) {
		
		this.card = card;
		this.username = username;
		this.contents = contents;
		this.timeStamp = timeStamp;
	}



	
}