package pl.serkus.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name  = "publishing_House")
public class PublishingHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "publishing_house_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany
	private Set<Book> book;

	public PublishingHouse() {
	}
	
	public PublishingHouse(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
