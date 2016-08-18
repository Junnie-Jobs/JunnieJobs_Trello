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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentId;
	
	@Getter
	@Setter
	@Column(name = "username", nullable=false)
	private String username;
	
	@Getter
	@Setter
	@Column(name = "timeStamp")
	private String timeStamp;
	
	@Getter
	@Setter
	@Column(name = "contents", length=1000, nullable=false)
	private String contents;
	
	@Getter(onMethod = @__( @JsonIgnore ))
	@Setter
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_comment_parent_id"))
	private Card card;
	
	public Comment(){}


	public Comment(Card card, String username, String contents, String timeStamp) {
		
		this.card = card;
		this.username = username;
		this.contents = contents;
		this.timeStamp = timeStamp;
		card.addComment(this);
	}
	





	
}