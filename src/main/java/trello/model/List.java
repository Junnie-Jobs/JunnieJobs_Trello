package trello.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "list")
public class List {
	private static List list = null;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "listName", length = 50, nullable = false)
	private String listName;
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_list_parent_id"))
	private Board board;

	public List(String listName) {
		this.listName = listName;
	}

	public static List getList() {
		return list;
	}

	public static void setList(List list) {
		List.list = list;
	}

}