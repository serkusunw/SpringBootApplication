package pl.serkus.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "publishing_house")
	private String publishing_house;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "release_date")
	private Date release_date;
	
	@Column(name = "count")
	private int count;
	
	@ManyToOne(cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToOne(cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Book(){
		
	}

	public Book(String title, String publishing_house, String description, Date release_date, int count, Author author,
			Category category) {
		this.title = title;
		this.publishing_house = publishing_house;
		this.description = description;
		this.release_date = release_date;
		this.count = count;
		this.author = author;
		this.category = category;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublishing_house() {
		return publishing_house;
	}

	public void setPublishing_house(String publishing_house) {
		this.publishing_house = publishing_house;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
